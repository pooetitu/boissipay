package org.esgi.boissipay.billing;


import org.esgi.boissipay.billing.models.Billing;

public interface BillingRepository {
    void save(Billing billing);
}
