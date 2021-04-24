package be.kdg.se3.opdracht.adapters;

import be.kdg.se3.opdracht.application.exceptions.AdapterException;
import be.kdg.se3.opdracht.application.dto.AnnulationDTO;
import be.kdg.se3.opdracht.application.dto.OrderDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * A formatter that uses JAXB to transfer an XML string into a {@link OrderDTO} and {@link AnnulationDTO}
 */
public class JAXBMessageFormatter implements MessageFormatter {
    @Override
    public OrderDTO formatOrder(String messageString) throws AdapterException {
        OrderDTO out;
        try {
            Reader reader = new StringReader(messageString);
            JAXBContext context = JAXBContext.newInstance(OrderDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            out = (OrderDTO) unmarshaller.unmarshal(reader);
        } catch (Exception e) {
            throw new AdapterException("Error during conversion to orderDTO", e);
        }
        return out;
    }

    @Override
    public AnnulationDTO formatAnnulation(String messageString) throws AdapterException {
        AnnulationDTO out;
        try {
            Reader reader = new StringReader(messageString);
            JAXBContext context = JAXBContext.newInstance(AnnulationDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            out = (AnnulationDTO) unmarshaller.unmarshal(reader);
        } catch (Exception e) {
            throw new AdapterException("Error during conversion to annulationDTO", e);
        }
        return out;
    }
}