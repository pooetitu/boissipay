package org.esgi.boissipay.billing.infra.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class PaymentEntity {
    @Id
    private String id;

    @OneToOne()
    private OperationEntity operation;

    private boolean billed;

    private LocalDate createdAt;

    private LocalDate payedAt;

    @ManyToOne
    private ContractEntity contract;

    public PaymentEntity() {
    }

    public PaymentEntity(String id, OperationEntity operation, boolean billed, LocalDate createdAt, LocalDate payedAt, ContractEntity contract) {
        this.id = id;
        this.operation = operation;
        this.billed = billed;
        this.createdAt = createdAt;
        this.payedAt = payedAt;
        this.contract = contract;
    }

    public LocalDate payedAt() {
        return payedAt;
    }

    public PaymentEntity setPayedAt(LocalDate payedAt) {
        this.payedAt = payedAt;
        return this;
    }

    public String id() {
        return id;
    }

    public PaymentEntity setId(String id) {
        this.id = id;
        return this;
    }

    public OperationEntity operation() {
        return operation;
    }

    public PaymentEntity setOperation(OperationEntity operation) {
        this.operation = operation;
        return this;
    }

    public ContractEntity contract() {
        return contract;
    }

    public PaymentEntity setContract(ContractEntity contract) {
        this.contract = contract;
        return this;
    }

    public boolean billed() {
        return billed;
    }

    public PaymentEntity setBilled(boolean billed) {
        this.billed = billed;
        return this;
    }

    public LocalDate createdAt() {
        return createdAt;
    }

    public PaymentEntity setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
