package org.esgi.boissipay.billing.use_case.handler;

import org.esgi.boissipay.billing.domain.event.SendInvoiceHandler;
import org.esgi.boissipay.billing.domain.models.Invoice;
import org.esgi.boissipay.billing.use_case.SetPaymentInvoiceRefUseCase;

public class SendInvoiceEventHandler implements SendInvoiceHandler {

    private final SetPaymentInvoiceRefUseCase setPaymentInvoiceRefUseCase;

    public SendInvoiceEventHandler(SetPaymentInvoiceRefUseCase setPaymentInvoiceRefUseCase) {
        this.setPaymentInvoiceRefUseCase = setPaymentInvoiceRefUseCase;
    }

    @Override
    public void onSendInvoice(Invoice invoice) {
        invoice.payments().forEach(payment -> setPaymentInvoiceRefUseCase.setInvoiceRef(payment.id(), invoice.invoiceRef()));
    }
}
