package org.esgi.boissipay.billing.domain.event;

import org.esgi.boissipay.billing.domain.models.Contract;
import org.esgi.boissipay.billing.domain.models.Invoice;
import org.esgi.boissipay.billing.domain.models.Operation;
import org.esgi.boissipay.billing.domain.models.Payment;

import java.util.Set;

public interface EventDispatcher {

    EventDispatcher setCreateContractHandlers(Set<CreateContractHandler> createContractHandlers);

    EventDispatcher setInvoiceSentHandlers(Set<InvoiceSentHandler> invoiceSentHandlers);

    EventDispatcher setPaymentSuccessHandlers(Set<PaymentSuccessHandler> paymentSuccessHandlers);

    EventDispatcher setProcessPaymentHandlers(Set<ProcessPaymentHandler> processPaymentHandlers);

    EventDispatcher setSendInvoiceHandlers(Set<SendInvoiceHandler> sendInvoiceHandlers);

    EventDispatcher setNewOperationHandlers(Set<NewOperationHandler> newOperationHandlers);

    void dispatchCreateContract(Contract contract);

    void dispatchProcessPayment(Payment payment);

    void dispatchPaymentSuccess(Payment payment);

    void dispatchSendInvoice(Invoice invoice);

    void dispatchInvoiceSent(Invoice invoice);

    void dispatchNewOperation(Operation operation);
}
