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

import java.util.Set;

public final class DefaultEventDispatcher implements EventDispatcher {

    private final Set<CreateContractHandler> createContractHandlers;
    private final Set<InvoiceSentHandler> invoiceSentHandlers;
    private final Set<PaymentSuccessHandler> paymentSuccessHandlers;
    private final Set<ProcessPaymentHandler> processPaymentHandlers;
    private final Set<SendInvoiceHandler> sendInvoiceHandlers;

    private final Set<NewOperationHandler> newOperationHandlers;

    public DefaultEventDispatcher(Set<CreateContractHandler> createContractHandlers,
                                  Set<InvoiceSentHandler> invoiceSentHandlers,
                                  Set<PaymentSuccessHandler> paymentSuccessHandlers,
                                  Set<ProcessPaymentHandler> processPaymentHandlers,
                                  Set<SendInvoiceHandler> sendInvoiceHandlers, Set<NewOperationHandler> newOperationHandlers) {
        this.createContractHandlers = createContractHandlers;
        this.invoiceSentHandlers = invoiceSentHandlers;
        this.paymentSuccessHandlers = paymentSuccessHandlers;
        this.processPaymentHandlers = processPaymentHandlers;
        this.sendInvoiceHandlers = sendInvoiceHandlers;
        this.newOperationHandlers = newOperationHandlers;
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
