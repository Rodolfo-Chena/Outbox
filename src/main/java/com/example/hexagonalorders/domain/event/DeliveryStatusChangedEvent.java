package com.example.hexagonalorders.domain.event;

public class DeliveryStatusChangedEvent {

    private final String deliveryId;
    private final String newStatus;

    public DeliveryStatusChangedEvent(String deliveryId, String newStatus) {
        this.deliveryId = deliveryId;
        this.newStatus = newStatus;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getNewStatus() {
        return newStatus;
    }
}
