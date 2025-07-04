package com.example.hexagonalorders.domain.model;

import com.example.hexagonalorders.domain.event.DeliveryCreatedEvent;
import com.example.hexagonalorders.domain.event.DeliveryStatusChangedEvent;
import com.example.hexagonalorders.domain.model.valueobject.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Delivery {

    private DeliveryId id;
    private RouteId route;
    private DeliveryPersonId assignedPerson;
    private Address destination;
    private OrderNumber relatedOrder;
    private LocalDateTime scheduledAt;
    private DeliveryStatus status;

    private final List<Object> domainEvents = new ArrayList<>();

    public Delivery(DeliveryId id, RouteId route, DeliveryPersonId assignedPerson,
                    Address destination, OrderNumber relatedOrder, LocalDateTime scheduledAt) {

        this.id = id;
        this.route = route;
        this.assignedPerson = assignedPerson;
        this.destination = destination;
        this.relatedOrder = relatedOrder;
        this.scheduledAt = scheduledAt;
        this.status = DeliveryStatus.PENDING;

        domainEvents.add(new DeliveryCreatedEvent(this.id.getValue()));
    }

    public void markAsDelivered() {
        if (!status.equals(DeliveryStatus.DELIVERED)) {
            this.status = DeliveryStatus.DELIVERED;
            domainEvents.add(new DeliveryStatusChangedEvent(id.getValue(), status.name()));
        }
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public List<Object> pullDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }
    public DeliveryId getId() {
    return id;
}

public RouteId getRoute() {
    return route;
}

public DeliveryPersonId getAssignedPerson() {
    return assignedPerson;
}

public OrderNumber getOrderNumber() {
    return relatedOrder;
}

public Address getDestination() {
    return destination;
}
public LocalDateTime getScheduledAt() {
    return scheduledAt;
}

}
