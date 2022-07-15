package org.esgi.boissipay.billing.domain.event;

import org.esgi.boissipay.billing.domain.models.Contract;
import org.esgi.boissipay.billing.domain.models.Invoice;
import org.esgi.boissipay.billing.domain.models.Operation;
import org.esgi.boissipay.billing.domain.models.Payment;

public interface EventDispatcher {
    void dispatchCreateContract(Contract contract);

    void dispatchProcessPayment(Payment payment);

    void dispatchPaymentSuccess(Payment payment);

    void dispatchSendInvoice(Invoice invoice);

    void dispatchInvoiceSent(Invoice invoice);

    void dispatchNewOperation(Operation operation);
}
