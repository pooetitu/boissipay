package org.esgi.boissipay.billing.domain.models;

import java.time.ZonedDateTime;
import java.util.List;

public class Invoice {
    private String invoiceRef;
    private String contractRef;
    private String contractId;
    private ContactPerson contactPerson;
    private ZonedDateTime instant;
    private List<Payment> payments;

    public Invoice() {
    }

    public Invoice(String invoiceRef, String contractRef, String contractId, ContactPerson contactPerson, ZonedDateTime instant, List<Payment> payments) {
        this.invoiceRef = invoiceRef;
        this.contractRef = contractRef;
        this.contractId = contractId;
        this.contactPerson = contactPerson;
        this.instant = instant;
        this.payments = payments;
    }

    public String invoiceRef() {
        return invoiceRef;
    }

    public Invoice setInvoiceRef(String invoiceRef) {
        this.invoiceRef = invoiceRef;
        return this;
    }

    public String contractRef() {
        return contractRef;
    }

    public Invoice setContractRef(String contractRef) {
        this.contractRef = contractRef;
        return this;
    }

    public String contractId() {
        return contractId;
    }

    public Invoice setContractId(String contractId) {
        this.contractId = contractId;
        return this;
    }

    public ContactPerson contactPerson() {
        return contactPerson;
    }

    public Invoice setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public ZonedDateTime instant() {
        return instant;
    }

    public Invoice setInstant(ZonedDateTime instant) {
        this.instant = instant;
        return this;
    }

    public List<Payment> payments() {
        return payments;
    }

    public Invoice setPayments(List<Payment> payments) {
        this.payments = payments;
        return this;
    }
}
