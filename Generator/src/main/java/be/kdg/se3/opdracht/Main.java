package be.kdg.se3.opdracht;

import be.kdg.se3.opdracht.adapters.JAXBMessageFormatter;
import be.kdg.se3.opdracht.adapters.RabbitMQ;
import be.kdg.se3.opdracht.application.generator.MessageGenerator;
import be.kdg.se3.opdracht.application.generator.OrderGenerator;
import be.kdg.se3.opdracht.application.properties.Properties;

public class Main {

    public static void main(String[] args) {
        MessageGenerator messageGenerator = new MessageGenerator(new OrderGenerator());
        messageGenerator.setMessageOutputService(new RabbitMQ(Properties.CONNECTIONSTRING, Properties.QUEUE_NAME,
                Properties.USERNAME, Properties.PASSWORD, new JAXBMessageFormatter()));

        messageGenerator.start();
    }
}
