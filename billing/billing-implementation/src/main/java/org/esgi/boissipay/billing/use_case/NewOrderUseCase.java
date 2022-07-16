package org.esgi.boissipay.billing.use_case;

import org.esgi.boissipay.billing.domain.models.Order;
import org.esgi.boissipay.billing.domain.repository.OrderRepository;

import java.util.Objects;
import java.util.UUID;

public final class NewOrderUseCase {

    private final OrderRepository orderRepository;

    public NewOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = Objects.requireNonNull(orderRepository);
    }

    public void newOrder(Order newOrder) {
        if (newOrder.orderRef() == null) {
            newOrder.setOrderRef(UUID.randomUUID().toString());
        }
        orderRepository.save(newOrder);
    }
}
