package be.kdg.se3.opdracht.adapters;

import be.kdg.se3.opdracht.application.exceptions.AdapterException;
import be.kdg.se3.opdracht.application.dto.AnnulationDTO;
import be.kdg.se3.opdracht.application.dto.OrderDTO;
import be.kdg.se3.opdracht.application.services.MessageOutputService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Adapts the domains MessageOutputService to a RabbitMQ queue
 * Errors during conversion of outgoing messages using the supplied formatter are logged in this class.
 */
public class RabbitMQ implements MessageOutputService {

    private final String connectionString;
    private final String username;
    private final String password;
    private final String queueName;
    private MessageFormatter formatter;
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

    public void initialize() throws AdapterException {
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

            logger.info("Initialisation RabbitMQ successful:");
            logger.info("Using host '" + connectionString + "'");
            logger.info("Using queue '" + queueName + "'");
        } catch (Exception e) {
            throw new AdapterException("Error during RabbitMQ channel initialisation", e);
        }
    }

    @Override
    public void sendOrder(OrderDTO msg) throws AdapterException {
        String message = this.formatter.formatOrder(msg);
        try {
            channel.basicPublish("", queueName, null, message.getBytes());
            logger.info("Send orderMessage from RabbitMQ to queue: " + queueName);
            logger.debug("orderMessage content: " + message);
        } catch (IOException e) {
            throw new AdapterException("Error during RabbitMQ channel sending orderMessage", e);
        }
    }

    @Override
    public void sendAnnulation(AnnulationDTO msg) throws AdapterException {
        String message = this.formatter.formatAnnulation(msg);
        try {
            channel.basicPublish("", queueName, null, message.getBytes());
            logger.info("Send annulationMessage from RabbitMQ to queue: " + queueName);
            logger.debug("annulationMessage content: " + message);
        } catch (IOException e) {
            throw new AdapterException("Error during RabbitMQ channel sending annulationMessage", e);
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
