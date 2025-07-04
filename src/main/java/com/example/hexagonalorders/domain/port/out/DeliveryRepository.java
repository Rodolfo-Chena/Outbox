package com.example.hexagonalorders.domain.port.out;

import com.example.hexagonalorders.domain.model.Delivery;
import java.util.Optional;

public interface DeliveryRepository {
    Delivery save(Delivery delivery);
    Optional<Delivery> findById(String deliveryId);
    

}
