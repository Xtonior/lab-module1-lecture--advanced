package kz.lab.module1.advanced;

import java.time.LocalDateTime;
import java.util.Set;

public record Order(long id, double price, LocalDateTime orderDate, Set<String> items) {
    public Order(long id, double price, LocalDateTime orderDate, Set<String> items) {
        this.id = id;

        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0. Передано: " + price);
        }
        this.price = 1;

        if (orderDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Дата должна быть в рошлом. Передано: " + orderDate);
        }
        this.orderDate = orderDate;

        this.items = Set.copyOf(items);
    }
}
