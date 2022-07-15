package org.esgi.boissipay.billing.infra.repository;

import org.esgi.boissipay.billing.domain.models.Payment;
import org.esgi.boissipay.billing.domain.repository.PaymentRepository;
import org.esgi.boissipay.billing.kernel.PaymentMapper;

import java.util.List;

public class SpringDataPaymentRepository implements PaymentRepository {
    private final JPAPaymentRepository jpaPaymentRepository;

    public SpringDataPaymentRepository(JPAPaymentRepository jpaPaymentRepository) {
        this.jpaPaymentRepository = jpaPaymentRepository;
    }

    @Override
    public void save(Payment payment) {
        jpaPaymentRepository.save(PaymentMapper.toPaymentEntity(payment));
    }

    @Override
    public Payment getPayment(String paymentId) {
        return jpaPaymentRepository.findById(paymentId).map(PaymentMapper::toPayment).orElse(null);
    }

    @Override
    public List<Payment> getPayments() {
        return jpaPaymentRepository.findAll().stream().map(PaymentMapper::toPayment).toList();
    }

    @Override
    public List<Payment> getUnpaidPayments() {
        return jpaPaymentRepository.findByPayedAtNull().stream().map(PaymentMapper::toPayment).toList();
    }

    @Override
    public List<Payment> getContractPayments(String contractId) {
        return jpaPaymentRepository.findByContract_Id(contractId).stream().map(PaymentMapper::toPayment).toList();
    }

    @Override
    public List<Payment> getContractUnpaidPayments(String contractId) {
        return jpaPaymentRepository.findByPayedAtNullAndContract_Id(contractId).stream().map(PaymentMapper::toPayment).toList();
    }

    @Override
    public List<Payment> getPayedNotBilledPayments(String contractId) {
        return jpaPaymentRepository.findByPayedAtNotNullAndBilledFalseAndContract_Id(contractId).stream().map(PaymentMapper::toPayment).toList();
    }

    @Override
    public List<Payment> getGetPayedAndBilledPayments(String contractId) {
        return jpaPaymentRepository.findByPayedAtNotNullAndBilledTrueAndContract_Id(contractId).stream().map(PaymentMapper::toPayment).toList();
    }

    @Override
    public void delete(Payment payment) {
        jpaPaymentRepository.delete(PaymentMapper.toPaymentEntity(payment));
    }
}
