package org.esgi.boissipay.billing.domain;

import java.time.ZonedDateTime;
import java.util.List;

public interface BillingRepository {
    void save(Billing billing);
    List<Billing> getBillOfLastMonth(ZonedDateTime endDate);
}
