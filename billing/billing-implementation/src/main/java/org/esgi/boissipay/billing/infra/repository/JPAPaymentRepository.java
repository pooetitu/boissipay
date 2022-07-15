package org.esgi.boissipay.billing.infra.repository;

import org.esgi.boissipay.billing.infra.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAPaymentRepository extends JpaRepository<PaymentEntity, String> {

    List<PaymentEntity> findByContractId(String contractId);
    List<PaymentEntity> findByPayedAtNull();
    List<PaymentEntity> findByPayedAtNullAndContractId(String contractId);

    List<PaymentEntity> findByInvoiceRef(String invoiceRef);

    List<PaymentEntity> findByPayedAtNotNullAndBilledFalseAndContractId(String contractId);

    List<PaymentEntity> findByPayedAtNotNullAndBilledTrueAndContractId(String contractId);
}
