package org.esgi.boissipay.billing.infra.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "contracts")
public class ContractEntity {
    @Id
    private String id;

    private String ref;

    private String type;

    private LocalDate createdAt;

    private LocalDate activatedAt;

    private LocalDate expireAt;

    private String status;

    @OneToMany(mappedBy = "contract")
    private List<PaymentEntity> payments;

    public ContractEntity() {
    }

    public ContractEntity(String id, String ref, String type, LocalDate createdAt, LocalDate activatedAt, LocalDate expireAt, String status, List<PaymentEntity> payments) {
        this.id = id;
        this.ref = ref;
        this.type = type;
        this.createdAt = createdAt;
        this.activatedAt = activatedAt;
        this.expireAt = expireAt;
        this.status = status;
        this.payments = payments;
    }

    public List<PaymentEntity> payments() {
        return payments;
    }

    public ContractEntity setPayments(List<PaymentEntity> payments) {
        this.payments = payments;
        return this;
    }

    public String id() {
        return id;
    }

    public ContractEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String ref() {
        return ref;
    }

    public ContractEntity setRef(String ref) {
        this.ref = ref;
        return this;
    }

    public String type() {
        return type;
    }

    public ContractEntity setType(String type) {
        this.type = type;
        return this;
    }

    public LocalDate createdAt() {
        return createdAt;
    }

    public ContractEntity setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDate activatedAt() {
        return activatedAt;
    }

    public ContractEntity setActivatedAt(LocalDate activatedAt) {
        this.activatedAt = activatedAt;
        return this;
    }

    public LocalDate expireAt() {
        return expireAt;
    }

    public ContractEntity setExpireAt(LocalDate expireAt) {
        this.expireAt = expireAt;
        return this;
    }

    public String status() {
        return status;
    }

    public ContractEntity setStatus(String status) {
        this.status = status;
        return this;
    }
}
