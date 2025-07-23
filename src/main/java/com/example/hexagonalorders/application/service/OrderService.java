package com.example.hexagonalorders.application.service;

import com.example.hexagonalorders.domain.event.DomainEvent;
import com.example.hexagonalorders.domain.model.Order;
import com.example.hexagonalorders.domain.model.OutboxMessage;
import com.example.hexagonalorders.domain.model.valueobject.OrderNumber;
import com.example.hexagonalorders.domain.port.in.OrderUseCase;
import com.example.hexagonalorders.domain.port.out.OrderNumberGenerator;
import com.example.hexagonalorders.domain.port.out.OrderRepository;
import com.example.hexagonalorders.domain.port.out.OutboxRepository;
import com.example.hexagonalorders.domain.service.OrderValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class OrderService implements OrderUseCase {

    private final OrderRepository orderRepository;
    private final OrderNumberGenerator orderNumberGenerator;
    private final OrderValidationService orderValidationService;
    private final ApplicationEventPublisher eventPublisher;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public Order createOrder(Order order) {
        // Validar
        orderValidationService.validateOrder(order);

        // Generar número de orden
        OrderNumber orderNumber = orderNumberGenerator.generate();
        Order orderWithNumber = new Order(
                orderNumber,
                order.getCustomerId(),
                order.getOrderDate(),
                order.getItems(),
                order.getStatus()
        );

        // Guardar la orden
        Order savedOrder = orderRepository.save(orderWithNumber);

        // Publicar eventos de dominio
        for (DomainEvent event : savedOrder.getDomainEvents()) {
            eventPublisher.publishEvent(event); // Internamente (opcional)

            // Guardar en outbox
            persistToOutbox(event, "Order", orderNumber.value());
        }

        savedOrder.clearDomainEvents();
        return savedOrder;
    }

    @Override
    public Optional<Order> getOrder(OrderNumber orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteOrder(OrderNumber orderNumber) {
        orderRepository.deleteByOrderNumber(orderNumber);
    }

    protected void persistToOutbox(DomainEvent event, String aggregateType, String aggregateId) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            String eventType = event.getClass().getSimpleName();
            UUID uuid = UUID.nameUUIDFromBytes(aggregateId.getBytes());

            OutboxMessage message = OutboxMessage.createPendingMessage(
                    aggregateType,
                    uuid,
                    eventType,
                    payload
            );
            outboxRepository.save(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to persist event to outbox", e);
        }
    }
}
