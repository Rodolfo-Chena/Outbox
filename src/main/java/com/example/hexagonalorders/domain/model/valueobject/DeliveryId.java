package com.example.hexagonalorders.domain.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public class DeliveryId {

    private final String value;

    public DeliveryId() {
        this.value = UUID.randomUUID().toString();
    }

    public DeliveryId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // Dos DeliveryId son iguales si su valor es igual
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryId)) return false;
        DeliveryId other = (DeliveryId) o;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
