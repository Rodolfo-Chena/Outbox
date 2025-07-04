package com.example.hexagonalorders.infrastructure.in.web.mapper;

import com.example.hexagonalorders.domain.model.Delivery;
import com.example.hexagonalorders.domain.model.valueobject.*;
import com.example.hexagonalorders.infrastructure.in.web.dto.CreateDeliveryRequest;
import com.example.hexagonalorders.infrastructure.in.web.dto.DeliveryDto;

public class DeliveryMapper {

    public static Delivery toDomain(CreateDeliveryRequest request) {
        return new Delivery(
            new DeliveryId(),
            new RouteId(request.routeId),
            new DeliveryPersonId(request.deliveryPersonId),
            new Address(
                request.destination.street,
                request.destination.city,
                request.destination.postalCode
            ),
            new OrderNumber(request.orderNumber),
            request.scheduledAt
        );
    }

    public static DeliveryDto toDto(Delivery delivery) {
        DeliveryDto dto = new DeliveryDto();
        dto.deliveryId = delivery.getId().getValue();
        dto.routeId = delivery.getRoute().getValue();
        dto.deliveryPersonId = delivery.getAssignedPerson().getValue();
        dto.orderNumber = delivery.getOrderNumber().toString(); // Cambio aquí
        dto.status = delivery.getStatus().name();
        dto.scheduledAt = delivery.getScheduledAt();

        DeliveryDto.AddressDto addressDto = new DeliveryDto.AddressDto();
        addressDto.street = delivery.getDestination().getStreet();  // Cambio aquí
        addressDto.city = delivery.getDestination().getCity();      // Cambio aquí
        addressDto.postalCode = delivery.getDestination().getPostalCode();  // Cambio aquí
        dto.destination = addressDto;

        return dto;
    }
}
