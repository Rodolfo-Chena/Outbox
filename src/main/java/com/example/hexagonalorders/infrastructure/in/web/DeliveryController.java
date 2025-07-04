package com.example.hexagonalorders.infrastructure.in.web;

import com.example.hexagonalorders.application.service.DeliveryService;
import com.example.hexagonalorders.infrastructure.in.web.dto.CreateDeliveryRequest;
import com.example.hexagonalorders.infrastructure.in.web.dto.DeliveryDto;
import com.example.hexagonalorders.infrastructure.in.web.mapper.DeliveryMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<DeliveryDto> createDelivery(@RequestBody CreateDeliveryRequest request) {
        var delivery = DeliveryMapper.toDomain(request);
        var created = deliveryService.createDelivery(delivery);
        var dto = DeliveryMapper.toDto(created);
        return ResponseEntity.ok(dto);
    }

   
}
