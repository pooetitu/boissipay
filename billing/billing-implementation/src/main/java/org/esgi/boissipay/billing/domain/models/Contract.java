package org.esgi.boissipay.billing.domain.models;

import java.time.LocalDate;

public record Contract(
    String id,
    String ref,
    String type,
    LocalDate createdAt,
    LocalDate activatedAt,
    LocalDate expireAt,
    String status
) {
}
