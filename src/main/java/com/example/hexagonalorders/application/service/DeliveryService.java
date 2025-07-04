package com.example.hexagonalorders.application.service;


import com.example.hexagonalorders.domain.model.Delivery;
import com.example.hexagonalorders.domain.port.out.DeliveryRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public Delivery getById(String deliveryId) {
        return deliveryRepository.findById(deliveryId)
            .orElseThrow(() -> new RuntimeException("Entrega no encontrada: " + deliveryId));
    }
  

}
