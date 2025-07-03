package com.example.hexagonalorders.domain.event;

public class DeliveryCreatedEvent {

    private final String deliveryId;

    public DeliveryCreatedEvent(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }
}
