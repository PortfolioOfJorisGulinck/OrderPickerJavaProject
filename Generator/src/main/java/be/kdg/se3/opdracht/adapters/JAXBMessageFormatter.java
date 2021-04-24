package be.kdg.se3.opdracht.adapters;

import be.kdg.se3.opdracht.application.exceptions.AdapterException;
import be.kdg.se3.opdracht.application.dto.AnnulationDTO;
import be.kdg.se3.opdracht.application.dto.OrderDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.*;

import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

/**
 * A formatter that uses JAXB to transfer a {@link OrderDTO} and {@link AnnulationDTO} into an XML string
 */
public class JAXBMessageFormatter implements MessageFormatter {

    @Override
    public String formatOrder(OrderDTO order) throws AdapterException {
        String out = "";
        try {
            Writer writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(OrderDTO.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(order, writer);
            out = writer.toString();
        } catch (Exception e) {
            throw new AdapterException("Error during conversion to DTO", e);
        }
        return out;
    }

    @Override
    public String formatAnnulation(AnnulationDTO annulation) throws AdapterException {
        String out = "";
        try {
            Writer writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(AnnulationDTO.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(annulation, writer);
            out = writer.toString();
        } catch (Exception e) {
            throw new AdapterException("Error during conversion to DTO", e);
        }
        return out;
    }
}