package org.esgi.boissipay.kafka.schema;

public record PaymentSuccess(
    String paymentId,
    String operationId,
    String customerRef
) {
}
