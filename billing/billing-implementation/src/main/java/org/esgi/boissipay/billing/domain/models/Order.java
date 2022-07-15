package org.esgi.boissipay.billing.domain.models;

public record Order(
    String orderRef,
    String operationId,
    String orderType,
    String productRef,
    String productLabel,
    String quantity,
    double priceWithoutTax,
    double priceTax,
    double priceWithTax
    ) {
}
