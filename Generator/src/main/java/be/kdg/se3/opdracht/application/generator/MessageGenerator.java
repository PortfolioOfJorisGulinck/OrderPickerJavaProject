package be.kdg.se3.opdracht.application.generator;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import be.kdg.se3.opdracht.application.persistence.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.kdg.se3.opdracht.application.domain.Annulation;
import be.kdg.se3.opdracht.application.dto.AnnulationDTO;
import be.kdg.se3.opdracht.application.services.MessageOutputService;
import be.kdg.se3.opdracht.application.domain.Order;
import be.kdg.se3.opdracht.application.dto.OrderDTO;
import be.kdg.se3.opdracht.application.exceptions.AdapterException;
import be.kdg.se3.opdracht.application.properties.Properties;

/**
 * Generator responsible for the creation and sending of the Orders in a messageformat
 * If not {@link MessageOutputService} is set, the message will not be send to a communication interface, but will be stored
 * If not {@link StorageService} is set, the message will be send to a communication interface, but not be stored
 */
public class MessageGenerator {

    private List<Annulation> annulations = new ArrayList<>();
    private MessageOutputService messageOutputService;
    private Generator generator;
    private ScheduledExecutorService executorService;
    private StorageService storageService;

    private Logger logger = LoggerFactory.getLogger(MessageGenerator.class);

    public MessageGenerator(Generator generator) {
        this.generator = generator;
    }

    public void setMessageOutputService(MessageOutputService messageOutputService) {
        this.messageOutputService = messageOutputService;
    }

    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    public void start() {
        try {
            this.messageOutputService.initialize();

            /*
            Creates a single-threaded executor that can schedule commands to run after a given delay,
            or to execute periodically. (Note however that if this single thread terminates due to a
            failure during execution prior to shutdown, a new one will take its place if needed to execute
            subsequent tasks.) Tasks are guaranteed to execute sequentially, and no more than one task will
            be active at any given time. Unlike the otherwise equivalent newScheduledThreadPool(1, threadFactory)
            the returned executor is guaranteed not to be reconfigurable to use additional threads.
             */
            executorService = newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(() -> {

                handleAnnulations();

                Order order = (Order) generator.generate();

                sendMessageToQueue(order);

                if (order.getOrderId() % Properties.ANNULATION_COUNT_INTERVAL == 0) {
                    Annulation newAnnulation = new Annulation(Properties.ANNULATION_SENDING_DELAY, order.getOrderId());
                    annulations.add(newAnnulation);
                }
            }, 0, Properties.GENERATOR_INTERVAL, TimeUnit.MILLISECONDS);
        } catch (AdapterException e) {
            logger.error("Error during RabbitMQ channel initialisation", e);
        }
    }

    private void handleAnnulations() {
        List<Annulation> todoAnnulations = new ArrayList<>();
        for (Annulation annulation : annulations) {
            annulation.setSendingDelay(annulation.getSendingDelay() - 1);
            if (annulation.getSendingDelay() < 0) {
                todoAnnulations.add(annulation);
            }
        }

        for (Annulation todoAnulation : todoAnnulations) {
            try {
                messageOutputService.sendAnnulation(new AnnulationDTO(todoAnulation.getOrderId()));
                logger.info("Annulation message successfully send - {}", todoAnulation.getOrderId());
            } catch (AdapterException e) {
                logger.error("Error during sending annulation message: " + todoAnulation.toString(), e);
                try {
                    messageOutputService.shutdown();
                } catch (AdapterException e1) {
                    logger.error("Not able to stop the MessageOutputService ", e);
                    stop();
                }
            }
        }
        annulations.removeAll(todoAnnulations);
    }

    private void sendMessageToQueue(Order order) {
        try {
            messageOutputService.sendOrder(new OrderDTO(order.getOrderId(), order.getCostumerId(), order.getPrice(), order.getItems()));
            logger.info("Order message successfully send {}", order);
        } catch (AdapterException e) {
            logger.error("Error during sending order message: " + order.toString(), e);
            try {
                messageOutputService.shutdown();
            } catch (AdapterException e1) {
                logger.error("Not able to stop the MessageOutputService ", e);
                stop();
            }
        }
    }

    private void stop() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
