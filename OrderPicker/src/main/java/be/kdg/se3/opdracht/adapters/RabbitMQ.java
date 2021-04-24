package be.kdg.se3.opdracht.adapters;

import be.kdg.se3.opdracht.application.exceptions.AdapterException;
import be.kdg.se3.opdracht.application.listeners.AnnulationInputListener;
import be.kdg.se3.opdracht.application.listeners.OrderInputListener;
import be.kdg.se3.opdracht.application.services.MessageInputService;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Adapts a RabbitMQ queue endpoint to the domains MessageInputService
 * Errors during conversion of incoming messages using the supplied formatter are logged in this class and reported to the listener
 */
public class RabbitMQ implements MessageInputService {

    private final String connectionString;
    private final String username;
    private final String password;
    private final String queueName;
    private final MessageFormatter formatter;
    private Connection connection;
    private Channel channel;

    private Logger logger = LoggerFactory.getLogger(RabbitMQ.class);

    public RabbitMQ(String connectionString, String queueName, String username, String password, MessageFormatter formatter) {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
        this.queueName = queueName;
        this.formatter = formatter;
    }

    @Override
    public void initialize(OrderInputListener orderInputListener, AnnulationInputListener annulationInputListener) throws AdapterException {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(connectionString);
            factory.setUsername(username);
            factory.setPassword(password);

            factory.setRequestedHeartbeat(30);
            factory.setConnectionTimeout(30000);

            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.queueDeclare(queueName,
                    true, /* durable */
                    false, /* non-exclusive */
                    false, /* do not auto delete */
                    null); /* no other construction arguments */

            logger.info("Using uri '" + connectionString + "'");
            logger.info("Using queue '" + queueName + "'");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    logger.info("Received message from RabbitMQ queue " + queueName);
                    String content = new String(body, "UTF-8");

                    if (content.contains("annulation") && annulationInputListener != null) {
                        try {
                            annulationInputListener.onReceive(formatter.formatAnnulation(content));
                            logger.info("Delivered annulation message to listener");
                        } catch (AdapterException e) {
                            annulationInputListener.onError(e, content);
                            logger.error("Exception during format conversion", e);
                        } catch (Exception e) {
                            logger.error("Exception during callback to listener", e);
                        }
                    } else if (content.contains("order") && orderInputListener != null) {
                        try {
                            orderInputListener.onReceive(formatter.formatOrder(content));
                            logger.info("Delivered order message to listener");
                        } catch (AdapterException e) {
                            orderInputListener.onError(e, content);
                        } catch (Exception e) {
                            logger.error("Exception during callback to listener", e);
                        }
                    }
                    logger.debug("Message content: " + content);
                }
            };
            channel.basicConsume(queueName, true, consumer);
        } catch (Exception e) {
            throw new AdapterException("Error during RabbitMQ channel initialisation", e);
        }
    }

    @Override
    public void shutdown() throws AdapterException {
        try {
            channel.close();
            connection.close();
        } catch (Exception e) {
            throw new AdapterException("Unable to close connection to RabbitMQ", e);
        }
    }
}
