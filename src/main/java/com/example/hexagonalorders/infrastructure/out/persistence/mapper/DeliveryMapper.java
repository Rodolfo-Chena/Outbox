package com.example.hexagonalorders.infrastructure.out.persistence.mapper;

import com.example.hexagonalorders.domain.model.Delivery;
import com.example.hexagonalorders.domain.model.valueobject.*;
import com.example.hexagonalorders.infrastructure.out.persistence.entity.DeliveryEntity;

public class DeliveryMapper {

    public static DeliveryEntity toEntity(Delivery delivery) {
        DeliveryEntity entity = new DeliveryEntity();
        entity.setDeliveryId(delivery.getId().getValue());
        entity.setRouteId(delivery.getRoute().getValue());
        entity.setDeliveryPersonId(delivery.getAssignedPerson().getValue());
        entity.setOrderNumber(delivery.getOrderNumber().toString());
        entity.setStatus(delivery.getStatus().name());
        entity.setScheduledAt(delivery.getScheduledAt());
        entity.setStreet(delivery.getDestination().getStreet());
        entity.setCity(delivery.getDestination().getCity());
        entity.setPostalCode(delivery.getDestination().getPostalCode());
        return entity;
    }

    public static Delivery toDomain(DeliveryEntity entity) {
        return new Delivery(
            new DeliveryId(entity.getDeliveryId()),
            new RouteId(entity.getRouteId()),
            new DeliveryPersonId(entity.getDeliveryPersonId()),
            new Address(
                entity.getStreet(),
                entity.getCity(),
                entity.getPostalCode()
            ),
            new OrderNumber(entity.getOrderNumber()),
            entity.getScheduledAt()
        );
    }
}
