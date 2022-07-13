package org.esgi.boissipay.billing.infra;

import org.esgi.boissipay.billing.models.Billing;
import org.esgi.boissipay.kafka.schema.NewContract;

import java.time.ZonedDateTime;

public class BillingFactory {
    public static Billing createFrom(NewContract contract) {
        return new Billing(ZonedDateTime.now(), contract.name());
    }
}
