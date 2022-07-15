package org.esgi.boissipay.billing.infra.repository;

import org.esgi.boissipay.billing.infra.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAPaymentRepository extends JpaRepository<PaymentEntity, String> {

    List<PaymentEntity> findByContract_Id(String contractId);
    List<PaymentEntity> findByPayedAtNull();
    List<PaymentEntity> findByPayedAtNullAndContract_Id(String contractId);

    List<PaymentEntity> findByPayedAtNotNullAndBilledFalseAndContract_Id(String contractId);

    List<PaymentEntity> findByPayedAtNotNullAndBilledTrueAndContract_Id(String contractId);
}
