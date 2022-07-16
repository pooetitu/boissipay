package org.esgi.boissipay.billing.infra.repository;

import org.esgi.boissipay.billing.domain.models.Payment;
import org.esgi.boissipay.billing.domain.repository.OperationRepository;
import org.esgi.boissipay.billing.domain.repository.PaymentRepository;
import org.esgi.boissipay.billing.kernel.PaymentMapper;

import java.util.List;

public class SpringDataPaymentRepository implements PaymentRepository {
    private final JPAPaymentRepository jpaPaymentRepository;

    private final OperationRepository operationRepository;

    public SpringDataPaymentRepository(JPAPaymentRepository jpaPaymentRepository, OperationRepository operationRepository) {
        this.jpaPaymentRepository = jpaPaymentRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public void save(Payment payment) {
        jpaPaymentRepository.save(PaymentMapper.toPaymentEntity(payment));
    }

    @Override
    public Payment getPayment(String paymentId) {
        return jpaPaymentRepository.findById(paymentId)
            .map(paymentEntity -> PaymentMapper.toPayment(paymentEntity, operationRepository.getOperation(paymentEntity.operationId())))
            .orElse(null);
    }

    @Override
    public List<Payment> getPayments() {
        return jpaPaymentRepository.findAll().stream()
            .map(paymentEntity -> PaymentMapper.toPayment(paymentEntity, operationRepository.getOperation(paymentEntity.operationId())))
            .toList();
    }

    @Override
    public List<Payment> getUnpaidPayments() {
        return jpaPaymentRepository.findByPayedAtNull().stream()
            .map(paymentEntity -> PaymentMapper.toPayment(paymentEntity, operationRepository.getOperation(paymentEntity.operationId())))
            .toList();
    }

    @Override
    public List<Payment> getByInvoiceRef(String invoiceRef) {
        return jpaPaymentRepository.findByInvoiceRef(invoiceRef).stream()
            .map(paymentEntity -> PaymentMapper.toPayment(paymentEntity, operationRepository.getOperation(paymentEntity.operationId())))
            .toList();
    }

    @Override
    public List<Payment> getContractPayments(String contractId) {
        return jpaPaymentRepository.findByContractId(contractId).stream()
            .map(paymentEntity -> PaymentMapper.toPayment(paymentEntity, operationRepository.getOperation(paymentEntity.operationId())))
            .toList();
    }

    @Override
    public List<Payment> getContractUnpaidPayments(String contractId) {
        return jpaPaymentRepository.findByPayedAtNullAndContractId(contractId).stream()
            .map(paymentEntity -> PaymentMapper.toPayment(paymentEntity, operationRepository.getOperation(paymentEntity.operationId())))
            .toList();
    }

    @Override
    public List<Payment> getPayedNotBilledPayments(String contractId) {
        return jpaPaymentRepository.findByPayedAtNotNullAndBilledFalseAndContractId(contractId).stream()
            .map(paymentEntity -> PaymentMapper.toPayment(paymentEntity, operationRepository.getOperation(paymentEntity.operationId())))
            .toList();
    }

    @Override
    public List<Payment> getGetPayedAndBilledPayments(String contractId) {
        return jpaPaymentRepository.findByPayedAtNotNullAndBilledTrueAndContractId(contractId).stream()
            .map(paymentEntity -> PaymentMapper.toPayment(paymentEntity, operationRepository.getOperation(paymentEntity.operationId())))
            .toList();
    }

    @Override
    public void delete(Payment payment) {
        jpaPaymentRepository.delete(PaymentMapper.toPaymentEntity(payment));
    }
}
