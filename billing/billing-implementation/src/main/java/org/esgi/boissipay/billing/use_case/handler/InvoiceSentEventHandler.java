package org.esgi.boissipay.billing.use_case.handler;

import org.esgi.boissipay.billing.domain.event.InvoiceSentHandler;
import org.esgi.boissipay.billing.domain.models.Invoice;
import org.esgi.boissipay.billing.use_case.BillPaymentUseCase;

public class InvoiceSentEventHandler implements InvoiceSentHandler {
    private final BillPaymentUseCase billPaymentUseCase;

    public InvoiceSentEventHandler(BillPaymentUseCase billPaymentUseCase) {
        this.billPaymentUseCase = billPaymentUseCase;
    }

    @Override
    public void onInvoiceSent(Invoice invoice) {
        invoice.payments().forEach(payment -> billPaymentUseCase.validatePayment(payment.id()));
    }
}
