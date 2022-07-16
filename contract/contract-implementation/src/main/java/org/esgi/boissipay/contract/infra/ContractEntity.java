package org.esgi.boissipay.contract.infra;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

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

    private String subscriberRef;

    private String subscriberType;

    private String customerId;

    private String customerRef;

    private String contactCcuid;

    private String contactGender;

    private String contactFirstName;

    private String contactLastName;

    private String contactEmail;

    private String contactPhone;

    public ContractEntity(String id, String ref, String type, LocalDate createdAt, LocalDate activatedAt, LocalDate expireAt,
                          String status, String subscriberRef, String subscriberType, String customerId, String customerRef,
                          String contactCcuid, String contactGender, String contactFirstName, String contactLastName,
                          String contactEmail, String contactPhone) {
        this.id = id;
        this.ref = ref;
        this.type = type;
        this.createdAt = createdAt;
        this.activatedAt = activatedAt;
        this.expireAt = expireAt;
        this.status = status;
        this.subscriberRef = subscriberRef;
        this.subscriberType = subscriberType;
        this.customerId = customerId;
        this.customerRef = customerRef;
        this.contactCcuid = contactCcuid;
        this.contactGender = contactGender;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public ContractEntity() {
    }

    public ContractEntity setId(String id) {
        this.id = id;
        return this;
    }

    public ContractEntity setRef(String ref) {
        this.ref = ref;
        return this;
    }

    public ContractEntity setType(String type) {
        this.type = type;
        return this;
    }

    public ContractEntity setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ContractEntity setActivatedAt(LocalDate activatedAt) {
        this.activatedAt = activatedAt;
        return this;
    }

    public ContractEntity setExpireAt(LocalDate expireAt) {
        this.expireAt = expireAt;
        return this;
    }

    public ContractEntity setStatus(String status) {
        this.status = status;
        return this;
    }

    public ContractEntity setSubscriberRef(String subscriberRef) {
        this.subscriberRef = subscriberRef;
        return this;
    }

    public ContractEntity setSubscriberType(String subscriberType) {
        this.subscriberType = subscriberType;
        return this;
    }

    public ContractEntity setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public ContractEntity setCustomerRef(String customerRef) {
        this.customerRef = customerRef;
        return this;
    }

    public ContractEntity setContactCcuid(String contactCcuid) {
        this.contactCcuid = contactCcuid;
        return this;
    }

    public ContractEntity setContactGender(String contactGender) {
        this.contactGender = contactGender;
        return this;
    }

    public ContractEntity setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
        return this;
    }

    public ContractEntity setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
        return this;
    }

    public ContractEntity setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public ContractEntity setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public String id() {
        return id;
    }

    public String ref() {
        return ref;
    }

    public String type() {
        return type;
    }

    public LocalDate createdAt() {
        return createdAt;
    }

    public LocalDate activatedAt() {
        return activatedAt;
    }

    public LocalDate expireAt() {
        return expireAt;
    }

    public String status() {
        return status;
    }

    public String subscriberRef() {
        return subscriberRef;
    }

    public String subscriberType() {
        return subscriberType;
    }

    public String customerId() {
        return customerId;
    }

    public String customerRef() {
        return customerRef;
    }

    public String contactCcuid() {
        return contactCcuid;
    }

    public String contactGender() {
        return contactGender;
    }

    public String contactFirstName() {
        return contactFirstName;
    }

    public String contactLastName() {
        return contactLastName;
    }

    public String contactEmail() {
        return contactEmail;
    }

    public String contactPhone() {
        return contactPhone;
    }
}
