package com.example.hexagonalorders.infrastructure.out.persistence.repository;

import com.example.hexagonalorders.domain.model.Delivery;
import com.example.hexagonalorders.domain.port.out.DeliveryRepository;
import com.example.hexagonalorders.infrastructure.out.persistence.entity.DeliveryEntity;
import com.example.hexagonalorders.infrastructure.out.persistence.mapper.DeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
@Component
public class DeliveryRepositoryAdapter implements DeliveryRepository {

    private final DeliveryJpaRepository deliveryJpaRepository;

    @Autowired
    public DeliveryRepositoryAdapter(DeliveryJpaRepository deliveryJpaRepository) {
        this.deliveryJpaRepository = deliveryJpaRepository;
    }

    @Override
    public Delivery save(Delivery delivery) {
        DeliveryEntity entity = DeliveryMapper.toEntity(delivery);
        DeliveryEntity saved = deliveryJpaRepository.save(entity);
        return DeliveryMapper.toDomain(saved);
    }

    @Override
    public Optional<Delivery> findById(String deliveryId) {
    return deliveryJpaRepository.findById(UUID.fromString(deliveryId))
        .map(DeliveryMapper::toDomain);
    }
}
