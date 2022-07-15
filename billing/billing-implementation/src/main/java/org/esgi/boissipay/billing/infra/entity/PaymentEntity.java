package org.esgi.boissipay.billing.infra.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class PaymentEntity {
    @Id
    private String id;

    private String operationId;

    private boolean billed;

    private LocalDate createdAt;

    private LocalDate payedAt;

    private String contractId;

    public PaymentEntity() {
    }

    public PaymentEntity(String id, String operationId, boolean billed, LocalDate createdAt, LocalDate payedAt, String contractId) {
        this.id = id;
        this.operationId = operationId;
        this.billed = billed;
        this.createdAt = createdAt;
        this.payedAt = payedAt;
        this.contractId = contractId;
    }

    public String operationId() {
        return operationId;
    }

    public PaymentEntity setOperationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    public String contractId() {
        return contractId;
    }

    public PaymentEntity setContractId(String contractId) {
        this.contractId = contractId;
        return this;
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
