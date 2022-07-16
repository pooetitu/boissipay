package org.esgi.boissipay.billing.infra;


import org.esgi.boissipay.billing.domain.event.CreateContractHandler;
import org.esgi.boissipay.billing.domain.event.EventDispatcher;
import org.esgi.boissipay.billing.domain.event.InvoiceSentHandler;
import org.esgi.boissipay.billing.domain.event.NewOperationHandler;
import org.esgi.boissipay.billing.domain.event.PaymentSuccessHandler;
import org.esgi.boissipay.billing.domain.event.ProcessPaymentHandler;
import org.esgi.boissipay.billing.domain.event.SendInvoiceHandler;
import org.esgi.boissipay.billing.domain.models.Contract;
import org.esgi.boissipay.billing.domain.models.Invoice;
import org.esgi.boissipay.billing.domain.models.Operation;
import org.esgi.boissipay.billing.domain.models.Payment;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class DefaultEventDispatcher implements EventDispatcher {

    private Set<CreateContractHandler> createContractHandlers;
    private Set<InvoiceSentHandler> invoiceSentHandlers;
    private Set<PaymentSuccessHandler> paymentSuccessHandlers;
    private Set<ProcessPaymentHandler> processPaymentHandlers;
    private Set<SendInvoiceHandler> sendInvoiceHandlers;

    private Set<NewOperationHandler> newOperationHandlers;

    public DefaultEventDispatcher() {
        createContractHandlers = new HashSet<>();
        invoiceSentHandlers = new HashSet<>();
        paymentSuccessHandlers = new HashSet<>();
        processPaymentHandlers = new HashSet<>();
        sendInvoiceHandlers = new HashSet<>();
        newOperationHandlers = new HashSet<>();
    }

    public DefaultEventDispatcher setCreateContractHandlers(Set<CreateContractHandler> createContractHandlers) {
        this.createContractHandlers = Objects.requireNonNull(createContractHandlers);
        return this;
    }

    public DefaultEventDispatcher setInvoiceSentHandlers(Set<InvoiceSentHandler> invoiceSentHandlers) {
        this.invoiceSentHandlers = Objects.requireNonNull(invoiceSentHandlers);
        return this;
    }

    public DefaultEventDispatcher setPaymentSuccessHandlers(Set<PaymentSuccessHandler> paymentSuccessHandlers) {
        this.paymentSuccessHandlers = Objects.requireNonNull(paymentSuccessHandlers);
        return this;
    }

    public DefaultEventDispatcher setProcessPaymentHandlers(Set<ProcessPaymentHandler> processPaymentHandlers) {
        this.processPaymentHandlers = Objects.requireNonNull(processPaymentHandlers);
        return this;
    }

    public DefaultEventDispatcher setSendInvoiceHandlers(Set<SendInvoiceHandler> sendInvoiceHandlers) {
        this.sendInvoiceHandlers = Objects.requireNonNull(sendInvoiceHandlers);
        return this;
    }

    public DefaultEventDispatcher setNewOperationHandlers(Set<NewOperationHandler> newOperationHandlers) {
        this.newOperationHandlers = Objects.requireNonNull(newOperationHandlers);
        return this;
    }

    @Override
    public void dispatchCreateContract(Contract contract) {
        createContractHandlers.forEach(handler -> handler.onCreateContract(contract));
    }

    @Override
    public void dispatchProcessPayment(Payment payment) {
        processPaymentHandlers.forEach(handler -> handler.onProcessPayment(payment));
    }

    @Override
    public void dispatchPaymentSuccess(Payment payment) {
        paymentSuccessHandlers.forEach(handler -> handler.onPaymentSuccess(payment));
    }

    @Override
    public void dispatchSendInvoice(Invoice invoice) {
        sendInvoiceHandlers.forEach(handler -> handler.onSendInvoice(invoice));
    }

    @Override
    public void dispatchInvoiceSent(Invoice invoice) {
        invoiceSentHandlers.forEach(handler -> handler.onInvoiceSent(invoice));
    }

    @Override
    public void dispatchNewOperation(Operation operation) {
        newOperationHandlers.forEach(handler -> handler.onNewOperation(operation));
    }
}
