package org.esgi.boissipay.billing.infra.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "operations")
public class OperationEntity {

    @Id
    private String id;

    private String customerRef;

    @OneToOne(optional = true, mappedBy = "operation")
    private PaymentEntity payment;

    @ManyToOne
    private ContractEntity contract;

    @OneToMany(mappedBy = "operation")
    private List<OrderEntity> orders;

    public OperationEntity() {
    }

    public OperationEntity(String id, String customerRef, PaymentEntity payment, ContractEntity contract, List<OrderEntity> orders) {
        this.id = id;
        this.customerRef = customerRef;
        this.payment = payment;
        this.contract = contract;
        this.orders = orders;
    }

    public String id() {
        return id;
    }

    public OperationEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String customerRef() {
        return customerRef;
    }

    public OperationEntity setCustomerRef(String customerRef) {
        this.customerRef = customerRef;
        return this;
    }

    public PaymentEntity payment() {
        return payment;
    }

    public OperationEntity setPayment(PaymentEntity payment) {
        this.payment = payment;
        return this;
    }

    public ContractEntity contract() {
        return contract;
    }

    public OperationEntity setContract(ContractEntity contract) {
        this.contract = contract;
        return this;
    }

    public List<OrderEntity> orders() {
        return orders;
    }

    public OperationEntity setOrders(List<OrderEntity> orders) {
        this.orders = orders;
        return this;
    }
}
