package be.kdg.se3.opdracht.application.domain;

import be.kdg.se3.opdracht.application.dto.AnnulationDTO;

public class Annulation {
    private int orderId;

    public Annulation(AnnulationDTO annulationDTO) {
        this.orderId = annulationDTO.getOrderId();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
