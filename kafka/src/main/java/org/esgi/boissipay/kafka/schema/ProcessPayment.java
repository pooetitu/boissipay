package org.esgi.boissipay.kafka.schema;

public record ProcessPayment(
    String paymentId,
    String operationId,
    String personnCcuid,
    double priceWithoutTax,
    double priceTax,
    double priceWithTax
) {
}
