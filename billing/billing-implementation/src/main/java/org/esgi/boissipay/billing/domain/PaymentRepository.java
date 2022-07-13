package org.esgi.boissipay.billing.domain;

import java.time.ZonedDateTime;
import java.util.List;

public interface PaymentRepository {
    void save(Payment payment);
    List<Payment> getBillOfLastMonth(ZonedDateTime endDate);
}
