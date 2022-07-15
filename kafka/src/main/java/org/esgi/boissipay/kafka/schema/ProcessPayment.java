package org.esgi.boissipay.kafka.schema;

public record ProcessPayment(
    String paymentId,
    String operationId,
    String customerRef,
    double priceWithoutTax,
    double priceTax,
    double priceWithTax
) {
}
