package org.esgi.boissipay.billing.infra;

import org.esgi.boissipay.billing.domain.ContactPerson;
import org.esgi.boissipay.billing.domain.Payment;
import org.esgi.boissipay.kafka.schema.NewContract;

import java.time.ZonedDateTime;

public class PaymentFactory {
    public static Payment createFrom(NewContract contract) {
        return new Payment(ZonedDateTime.now(), new ContactPerson(contract.contactPerson().mail()), contract.name(), false);
    }
}
