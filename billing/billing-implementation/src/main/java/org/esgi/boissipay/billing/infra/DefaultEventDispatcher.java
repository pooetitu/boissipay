package org.esgi.boissipay.billing.infra;


import org.esgi.boissipay.billing.domain.CreateContractHandler;
import org.esgi.boissipay.billing.domain.EventDispatcher;
import org.esgi.boissipay.billing.domain.InvoiceSentHandler;
import org.esgi.boissipay.billing.domain.PaymentSuccessHandler;
import org.esgi.boissipay.billing.domain.ProcessPaymentHandler;
import org.esgi.boissipay.billing.domain.SendInvoiceHandler;
import org.esgi.boissipay.billing.domain.models.Contract;
import org.esgi.boissipay.billing.domain.models.Invoice;
import org.esgi.boissipay.billing.domain.models.Payment;

import java.util.Set;

public final class DefaultEventDispatcher implements EventDispatcher {

    private final Set<CreateContractHandler> createContractHandlers;
    private final Set<InvoiceSentHandler> invoiceSentHandlers;
    private final Set<PaymentSuccessHandler> paymentSuccessHandlers;
    private final Set<ProcessPaymentHandler> processPaymentHandlers;
    private final Set<SendInvoiceHandler> sendInvoiceHandlers;

    public DefaultEventDispatcher(Set<CreateContractHandler> createContractHandlers,
                                  Set<InvoiceSentHandler> invoiceSentHandlers,
                                  Set<PaymentSuccessHandler> paymentSuccessHandlers,
                                  Set<ProcessPaymentHandler> processPaymentHandlers,
                                  Set<SendInvoiceHandler> sendInvoiceHandlers) {
        this.createContractHandlers = createContractHandlers;
        this.invoiceSentHandlers = invoiceSentHandlers;
        this.paymentSuccessHandlers = paymentSuccessHandlers;
        this.processPaymentHandlers = processPaymentHandlers;
        this.sendInvoiceHandlers = sendInvoiceHandlers;
    }


    @Override
    public void dispatchCreateContact(Contract contract) {
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
}
