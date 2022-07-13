package org.esgi.boissipay.billing.infra;

import org.esgi.boissipay.billing.domain.PaymentRepository;
import org.esgi.boissipay.billing.domain.Payment;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public final class InMemoryPaymentRepository implements PaymentRepository {
    private final Set<Payment> payments;

    private InMemoryPaymentRepository(Set<Payment> payments) {
        this.payments = payments;
    }

    public static InMemoryPaymentRepository newEmptyInMemoryBillingRepository() {
       return new InMemoryPaymentRepository(new HashSet<>());
    }

    @Override
    public void save(Payment payment) {
        payments.add(payment);
    }

    @Override
    public List<Payment> getBillOfLastMonth(ZonedDateTime endDate) {
        var startDate = endDate.minusMonths(1);
        return payments.stream().filter(b -> b.instant().isAfter(startDate)).toList();
    }

    public int count() {
        return payments.size();
    }

    public Set<Payment> getBillings() {
        return Collections.unmodifiableSet(payments);
    }
}
