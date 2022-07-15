package org.esgi.boissipay.billing.infra.repository;

import org.esgi.boissipay.billing.domain.models.Order;
import org.esgi.boissipay.billing.domain.repository.OrderRepository;
import org.esgi.boissipay.billing.kernel.OrderMapper;

import java.util.List;

public class SpringDataOrderRepository implements OrderRepository {
    private final JPAOrderRepository jpaOrderRepository;

    public SpringDataOrderRepository(JPAOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public void save(Order order) {
        var orderEntity = OrderMapper.toOrderEntity(order);
        jpaOrderRepository.save(orderEntity);
    }

    @Override
    public Order getOrder(String orderRef) {
        return jpaOrderRepository.findById(orderRef).map(OrderMapper::toOrder).orElse(null);
    }

    @Override
    public List<Order> getOrders() {
        return jpaOrderRepository.findAll().stream().map(OrderMapper::toOrder).toList();
    }

    @Override
    public List<Order> getOrderByOperation(String operationId) {
        return jpaOrderRepository.findByOperationId(operationId).stream().map(OrderMapper::toOrder).toList();
    }

    @Override
    public void delete(Order order) {
        jpaOrderRepository.delete(OrderMapper.toOrderEntity(order));
    }
}

