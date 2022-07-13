package org.esgi.boissipay.billing.domain;

import java.util.List;

public interface PaymentRepository {
    void save(Payment payment);
    List<Payment> getUnpaidPayments();
    void delete(Payment payment);
}
