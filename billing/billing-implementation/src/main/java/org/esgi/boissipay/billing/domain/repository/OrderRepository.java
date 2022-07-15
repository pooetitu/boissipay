package org.esgi.boissipay.billing.domain.repository;

import org.esgi.boissipay.billing.domain.models.Order;

import java.util.List;

public interface OrderRepository {
    void save(Order order);

    Order getOrder(String orderRef);

    List<Order> getOrders();

    List<Order> getOrderByOperation(String operationId);

    void delete(Order order);
}
