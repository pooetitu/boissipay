package org.esgi.boissipay.billing.kernel;

import org.esgi.boissipay.kafka.schema.ContactPerson;
import org.esgi.boissipay.kafka.schema.Invoice;
import org.esgi.boissipay.kafka.schema.InvoiceSent;

public class InvoiceMapper {

    private InvoiceMapper() {
    }

    public static Invoice toKafkaInvoice(org.esgi.boissipay.billing.domain.models.Invoice invoice) {
        return new Invoice(
            invoice.invoiceRef(),
            invoice.contractRef(),
            invoice.contractId(),
            new ContactPerson(
                invoice.contactPerson().ccuid(),
                invoice.contactPerson().gender(),
                invoice.contactPerson().firstName(),
                invoice.contactPerson().lastName(),
                invoice.contactPerson().email(),
                invoice.contactPerson().phone()
            ),
            invoice.instant(),
            invoice.payments().stream().map(PaymentMapper::toProcessPayment).toList()
        );
    }

    public static org.esgi.boissipay.billing.domain.models.Invoice toInvoice(InvoiceSent invoiceSent) {
        return new org.esgi.boissipay.billing.domain.models.Invoice(
            invoiceSent.invoiceRef(),
            invoiceSent.contractRef(),
            invoiceSent.contractId(),
            null,
            null,
            invoiceSent.payments().stream().map(paymentId -> PaymentMapper.toPayment(paymentId, invoiceSent.invoiceRef())).toList()
        );
    }
}
