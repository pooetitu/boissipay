package org.esgi.boissipay.invoice.kernel;

import org.esgi.boissipay.invoice.domain.ContactPerson;
import org.esgi.boissipay.invoice.domain.Invoice;
import org.esgi.boissipay.invoice.domain.Payment;
import org.esgi.boissipay.kafka.schema.InvoiceSent;

public class InvoiceMapper {
    public static Invoice toInvoice(org.esgi.boissipay.kafka.schema.Invoice invoice) {
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
            invoice.payments().stream().map(processPayment -> new Payment(
                processPayment.paymentId(),
                processPayment.operationId(),
                processPayment.personnCcuid(),
                processPayment.priceWithoutTax(),
                processPayment.priceTax(),
                processPayment.priceWithTax()
            )).toList()
        );
    }

    public static InvoiceSent toInvoiceSent(Invoice invoice) {
        return new InvoiceSent(
            invoice.invoiceRef(),
            invoice.contractRef(),
            invoice.contractId(),
            invoice.payments().stream().map(Payment::paymentId).toList()
        );
    }
}
