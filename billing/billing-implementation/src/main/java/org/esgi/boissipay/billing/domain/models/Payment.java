package org.esgi.boissipay.billing.domain.models;

import java.time.LocalDate;

public class Payment{
    private String id;
    private Operation operation;
    private boolean billed;
    private LocalDate createdAt;
    private LocalDate payedAt;

    public Payment() {
    }

    public Payment(String id, Operation operation, boolean billed, LocalDate createdAt, LocalDate payedAt) {
        this.id = id;
        this.operation = operation;
        this.billed = billed;
        this.createdAt = createdAt;
        this.payedAt = payedAt;
    }

    public String id() {
        return id;
    }

    public Payment setId(String id) {
        this.id = id;
        return this;
    }

    public Operation operation() {
        return operation;
    }

    public Payment setOperation(Operation operation) {
        this.operation = operation;
        return this;
    }

    public boolean billed() {
        return billed;
    }

    public Payment setBilled(boolean billed) {
        this.billed = billed;
        return this;
    }

    public LocalDate createdAt() {
        return createdAt;
    }

    public Payment setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDate payedAt() {
        return payedAt;
    }

    public Payment setPayedAt(LocalDate payedAt) {
        this.payedAt = payedAt;
        return this;
    }
}
