package org.esgi.boissipay.billing.infra.repository;

import org.esgi.boissipay.billing.infra.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAOperationRepository extends JpaRepository<OperationEntity, String> {
    List<OperationEntity> findByPayment_Id(String paymentId);

    List<OperationEntity> findByContract_Id(String contractId);
}
