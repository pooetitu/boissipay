package org.esgi.boissipay.billing.infra;

import org.esgi.boissipay.billing.domain.BillingRepository;
import org.esgi.boissipay.billing.domain.Billing;

import java.util.Collections;
import java.util.HashSet;
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

    public int count() {
        return billings.size();
    }

    public Set<Billing> getBillings() {
        return Collections.unmodifiableSet(billings);
    }
}
