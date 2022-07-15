package org.esgi.boissipay.kafka.schema;

public record Payment(
    String paymentId,
    double amount
) {
}
