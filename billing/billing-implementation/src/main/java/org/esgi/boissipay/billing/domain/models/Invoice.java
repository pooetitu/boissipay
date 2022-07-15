package org.esgi.boissipay.billing.domain.models;

import java.time.ZonedDateTime;
import java.util.List;

public record Invoice(
    String invoiceRef,
    String contractRef,
    String contractId,
    ContactPerson contactPerson,
    ZonedDateTime instant,
    List<Payment> payments
) {
}