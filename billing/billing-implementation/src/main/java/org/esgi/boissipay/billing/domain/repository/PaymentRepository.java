package org.esgi.boissipay.billing.domain.repository;

import org.esgi.boissipay.billing.domain.models.Payment;

import java.util.List;

public interface PaymentRepository {
    void save(Payment payment);

    Payment getPayment(String paymentId);

    List<Payment> getPayments();

    List<Payment> getUnpaidPayments();

    List<Payment> getByInvoiceRef(String invoiceRef);

    List<Payment> getContractPayments(String contractId);

    List<Payment> getContractUnpaidPayments(String contractId);

    List<Payment> getPayedNotBilledPayments(String contractId);

    List<Payment> getGetPayedAndBilledPayments(String contractId);

    void delete(Payment payment);
}
