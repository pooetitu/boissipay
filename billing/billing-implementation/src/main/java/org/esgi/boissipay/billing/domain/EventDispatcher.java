package org.esgi.boissipay.billing.domain;

import org.esgi.boissipay.billing.domain.models.Contract;
import org.esgi.boissipay.billing.domain.models.Invoice;
import org.esgi.boissipay.billing.domain.models.Payment;

public interface EventDispatcher {
    void dispatchCreateContact(Contract contract);

    void dispatchProcessPayment(Payment payment);

    void dispatchPaymentSuccess(Payment payment);

    void dispatchSendInvoice(Invoice invoice);

    void dispatchInvoiceSent(Invoice invoice);
}
