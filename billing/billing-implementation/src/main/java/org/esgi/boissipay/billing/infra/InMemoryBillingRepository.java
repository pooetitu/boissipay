package org.esgi.boissipay.billing.infra;

import org.esgi.boissipay.billing.domain.BillingRepository;
import org.esgi.boissipay.billing.domain.Billing;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public final class InMemoryBillingRepository implements BillingRepository {
    private final Set<Billing> billings;

    private InMemoryBillingRepository(Set<Billing> billings) {
        this.billings = billings;
    }

    public static InMemoryBillingRepository newEmptyInMemoryBillingRepository() {
       return new InMemoryBillingRepository(new HashSet<>());
    }

    @Override
    public void save(Billing billing) {
        billings.add(billing);
    }

    @Override
    public List<Billing> getBillOfLastMonth(ZonedDateTime endDate) {
        var startDate = endDate.minusMonths(1);
        return billings.stream().filter(b -> b.instant().isAfter(startDate)).toList();
    }

    public int count() {
        return billings.size();
    }

    public Set<Billing> getBillings() {
        return Collections.unmodifiableSet(billings);
    }
}
