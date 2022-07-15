package org.esgi.boissipay.billing.infra.repository;

import org.esgi.boissipay.billing.infra.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAOrderRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByOperationId(String operationId);
}
