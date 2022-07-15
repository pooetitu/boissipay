package org.esgi.boissipay.billing.domain;

import java.time.ZonedDateTime;
import java.util.List;

public record Invoice(
    String contractName,
    ContactPerson contactPerson,
    ZonedDateTime instant,
    List<Payment> payments
) {
}
