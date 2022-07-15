package org.esgi.boissipay.billing.domain.models;

import java.util.List;

public record Operation(
    String id,
    String customerRef,
    String paymentId,
    List<Order> orders
) {
}
