package org.esgi.boissipay.payment.domain;

public class Payment {
    private String paymentId;
    private String operationId;
    private String personnCcuid;
    private double priceWithoutTax;
    private double priceTax;
    private double priceWithTax;

    public Payment() {
    }

    public Payment(String paymentId, String operationId, String personnCcuid, double priceWithoutTax, double priceTax, double priceWithTax) {
        this.paymentId = paymentId;
        this.operationId = operationId;
        this.personnCcuid = personnCcuid;
        this.priceWithoutTax = priceWithoutTax;
        this.priceTax = priceTax;
        this.priceWithTax = priceWithTax;
    }

    public String paymentId() {
        return paymentId;
    }

    public Payment setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public String operationId() {
        return operationId;
    }

    public Payment setOperationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    public String personnCcuid() {
        return personnCcuid;
    }

    public Payment setPersonnCcuid(String personnCcuid) {
        this.personnCcuid = personnCcuid;
        return this;
    }

    public double priceWithoutTax() {
        return priceWithoutTax;
    }

    public Payment setPriceWithoutTax(double priceWithoutTax) {
        this.priceWithoutTax = priceWithoutTax;
        return this;
    }

    public double priceTax() {
        return priceTax;
    }

    public Payment setPriceTax(double priceTax) {
        this.priceTax = priceTax;
        return this;
    }

    public double priceWithTax() {
        return priceWithTax;
    }

    public Payment setPriceWithTax(double priceWithTax) {
        this.priceWithTax = priceWithTax;
        return this;
    }
}
