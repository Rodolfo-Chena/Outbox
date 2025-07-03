package com.example.hexagonalorders.domain.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public class DeliveryPersonId {

    private final String value;

    public DeliveryPersonId() {
        this.value = UUID.randomUUID().toString();
    }

    public DeliveryPersonId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryPersonId)) return false;
        DeliveryPersonId other = (DeliveryPersonId) o;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
