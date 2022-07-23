package org.esgi.boissipay.billing.infra.repository;

import org.esgi.boissipay.billing.domain.models.Payment;
import org.esgi.boissipay.billing.domain.repository.OperationRepository;
import org.esgi.boissipay.billing.domain.repository.PaymentRepository;
import org.esgi.boissipay.billing.kernel.PaymentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SpringDataPaymentRepository implements PaymentRepository {
    private final JPAPaymentRepository jpaPaymentRepository;

    private final OperationRepository operationRepository;

    private static final Logger logger = LoggerFactory.getLogger(SpringDataPaymentRepository.class);

    public SpringDataPaymentRepository(JPAPaymentRepository jpaPaymentRepository, OperationRepository operationRepository) {
        this.jpaPaymentRepository = jpaPaymentRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public void save(Payment payment) {
        logger.info("Saving payment: " + payment);
        var paymentEntity = PaymentMapper.toPaymentEntity(payment);
        logger.info("Saving payment entity: " + paymentEntity);
        jpaPaymentRepository.save(paymentEntity);
    }

    @Override
    public Payment getPayment(String paymentId) {
        logger.info("Getting payment: " + paymentId);
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
