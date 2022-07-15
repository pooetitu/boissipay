package org.esgi.boissipay.billing.domain.models;

import java.time.LocalDate;

public record Payment(
    String id,
    Operation operation,
    boolean billed,
    LocalDate createdAt,
    LocalDate payedAt
) {
}
