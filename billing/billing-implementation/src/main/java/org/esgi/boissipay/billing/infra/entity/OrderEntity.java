package org.esgi.boissipay.billing.infra.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderEntity {
    @Id
    private String orderRef;
    @ManyToOne
    private OperationEntity operation;
    private String orderType;
    private String productRef;
    private String productLabel;
    private String quantity;
    private double priceWithoutTax;
    private double priceTax;
    private double priceWithTax;

    public OrderEntity() {
    }

    public OrderEntity(String orderRef, OperationEntity operation, String orderType, String productRef, String productLabel, String quantity, double priceWithoutTax, double priceTax, double priceWithTax) {
        this.orderRef = orderRef;
        this.operation = operation;
        this.orderType = orderType;
        this.productRef = productRef;
        this.productLabel = productLabel;
        this.quantity = quantity;
        this.priceWithoutTax = priceWithoutTax;
        this.priceTax = priceTax;
        this.priceWithTax = priceWithTax;
    }

    public String orderRef() {
        return orderRef;
    }

    public OrderEntity setOrderRef(String orderRef) {
        this.orderRef = orderRef;
        return this;
    }

    public OperationEntity operation() {
        return operation;
    }

    public OrderEntity setOperation(OperationEntity operation) {
        this.operation = operation;
        return this;
    }

    public String orderType() {
        return orderType;
    }

    public OrderEntity setOrderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    public String productRef() {
        return productRef;
    }

    public OrderEntity setProductRef(String productRef) {
        this.productRef = productRef;
        return this;
    }

    public String productLabel() {
        return productLabel;
    }

    public OrderEntity setProductLabel(String productLabel) {
        this.productLabel = productLabel;
        return this;
    }

    public String quantity() {
        return quantity;
    }

    public OrderEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public double priceWithoutTax() {
        return priceWithoutTax;
    }

    public OrderEntity setPriceWithoutTax(double priceWithoutTax) {
        this.priceWithoutTax = priceWithoutTax;
        return this;
    }

    public double priceTax() {
        return priceTax;
    }

    public OrderEntity setPriceTax(double priceTax) {
        this.priceTax = priceTax;
        return this;
    }

    public double priceWithTax() {
        return priceWithTax;
    }

    public OrderEntity setPriceWithTax(double priceWithTax) {
        this.priceWithTax = priceWithTax;
        return this;
    }
}
