package org.esgi.boissipay.billing.domain.models;

public class Order {
    private String orderRef;
    private String operationId;
    private String orderType;
    private String productRef;
    private String productLabel;
    private String quantity;
    private double priceWithoutTax;
    private double priceTax;
    private double priceWithTax;

    public Order(String orderRef, String operationId, String orderType, String productRef, String productLabel, String quantity, double priceWithoutTax, double priceTax, double priceWithTax) {
        this.orderRef = orderRef;
        this.operationId = operationId;
        this.orderType = orderType;
        this.productRef = productRef;
        this.productLabel = productLabel;
        this.quantity = quantity;
        this.priceWithoutTax = priceWithoutTax;
        this.priceTax = priceTax;
        this.priceWithTax = priceWithTax;
    }

    public Order() {
    }

    public String orderRef() {
        return orderRef;
    }

    public Order setOrderRef(String orderRef) {
        this.orderRef = orderRef;
        return this;
    }

    public String operationId() {
        return operationId;
    }

    public Order setOperationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    public String orderType() {
        return orderType;
    }

    public Order setOrderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    public String productRef() {
        return productRef;
    }

    public Order setProductRef(String productRef) {
        this.productRef = productRef;
        return this;
    }

    public String productLabel() {
        return productLabel;
    }

    public Order setProductLabel(String productLabel) {
        this.productLabel = productLabel;
        return this;
    }

    public String quantity() {
        return quantity;
    }

    public Order setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public double priceWithoutTax() {
        return priceWithoutTax;
    }

    public Order setPriceWithoutTax(double priceWithoutTax) {
        this.priceWithoutTax = priceWithoutTax;
        return this;
    }

    public double priceTax() {
        return priceTax;
    }

    public Order setPriceTax(double priceTax) {
        this.priceTax = priceTax;
        return this;
    }

    public double priceWithTax() {
        return priceWithTax;
    }

    public Order setPriceWithTax(double priceWithTax) {
        this.priceWithTax = priceWithTax;
        return this;
    }
}
