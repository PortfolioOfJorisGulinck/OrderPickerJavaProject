package be.kdg.se3.opdracht.application.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data transfer object used for conversion between stream formatAnnulation() and memory
 */
@XmlRootElement
public class AnnulationDTO {
    private int orderId;

    public AnnulationDTO() {
    }

    public AnnulationDTO(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    @XmlElement
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
