package com.example.hexagonalorders.infrastructure.out.persistence.repository;

import com.example.hexagonalorders.domain.model.Delivery;
import com.example.hexagonalorders.domain.port.out.DeliveryRepository;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class DeliveryRepositoryAdapter implements DeliveryRepository {

    private final Map<String, Delivery> storage = new HashMap<>();

    @Override
    public Delivery save(Delivery delivery) {
        storage.put(delivery.getId().getValue(), delivery);
        return delivery;
    }

    @Override
    public Optional<Delivery> findById(String deliveryId) {
        return Optional.ofNullable(storage.get(deliveryId));
    }
    


}
