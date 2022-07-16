package org.esgi.boissipay.billing.domain.models;

import java.util.List;

public class Operation {
    private String id;

    private String contractId;
    private ContactPerson contactPerson;
    private List<Order> orders;

    public Operation() {
    }

    public Operation(String id, String contractId, ContactPerson contactPerson, List<Order> orders) {
        this.id = id;
        this.contractId = contractId;
        this.contactPerson = contactPerson;
        this.orders = orders;
    }

    public String contractId() {
        return contractId;
    }

    public Operation setContractId(String contractId) {
        this.contractId = contractId;
        return this;
    }

    public String id() {
        return id;
    }

    public Operation setId(String id) {
        this.id = id;
        return this;
    }

    public ContactPerson contactPerson() {
        return contactPerson;
    }

    public Operation setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
        return this;
    }

    public List<Order> orders() {
        return orders;
    }

    public Operation setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
