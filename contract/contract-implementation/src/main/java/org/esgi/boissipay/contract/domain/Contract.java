package org.esgi.boissipay.contract.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public final class Contract {
    @JsonProperty
    private String contractId;
    @JsonProperty
    private String contractRef;
    @JsonProperty
    private String contractType;
    @JsonProperty
    private LocalDate createdAt;
    @JsonProperty
    private LocalDate activatedAt;
    @JsonProperty
    private LocalDate expireAt;
    @JsonProperty
    private String status;
    @JsonProperty
    private Subscriber subscriber;

    public String contractId() {
        return contractId;
    }

    public Contract setContractId(String contractId) {
        this.contractId = contractId;
        return this;
    }

    public String contractRef() {
        return contractRef;
    }

    public Contract setContractRef(String contractRef) {
        this.contractRef = contractRef;
        return this;
    }

    public String contractType() {
        return contractType;
    }

    public Contract setContractType(String contractType) {
        this.contractType = contractType;
        return this;
    }

    public LocalDate createdAt() {
        return createdAt;
    }

    public Contract setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDate activatedAt() {
        return activatedAt;
    }

    public Contract setActivatedAt(LocalDate activatedAt) {
        this.activatedAt = activatedAt;
        return this;
    }

    public LocalDate expireAt() {
        return expireAt;
    }

    public Contract setExpireAt(LocalDate expireAt) {
        this.expireAt = expireAt;
        return this;
    }

    public String status() {
        return status;
    }

    public Contract setStatus(String status) {
        this.status = status;
        return this;
    }

    public Subscriber subscriber() {
        return subscriber;
    }

    public Contract setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
        return this;
    }
}
