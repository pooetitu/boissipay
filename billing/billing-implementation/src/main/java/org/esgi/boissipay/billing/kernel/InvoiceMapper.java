package org.esgi.boissipay.billing.kernel;

import org.esgi.boissipay.billing.model.OperationResponse;
import org.esgi.boissipay.billing.model.OperationResponseAllOfInvoice;
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

    public static org.esgi.boissipay.billing.model.Invoice toInvoiceResponse(org.esgi.boissipay.billing.domain.models.Invoice invoice) {
        org.esgi.boissipay.billing.model.Invoice returnInvoice = new org.esgi.boissipay.billing.model.Invoice();
        returnInvoice.setInvoiceId(invoice.invoiceRef());
        returnInvoice.setOperations(invoice.payments().stream().map(payment -> {
            var operation = new OperationResponse();
            var operationInvoice = new OperationResponseAllOfInvoice();
            operationInvoice.setInvoiceRef(invoice.invoiceRef());
            operationInvoice.setCreatedAt(payment.createdAt());

            operation.setOperationId(payment.operation().id());
            operation.setInvoice(operationInvoice);
            operation.setOperationAt(payment.createdAt());
            operation.setOperationRef("RANDOM");
            operation.setCustomerRef(invoice.contactPerson().ccuid());
            return operation;
        }).toList());
        return returnInvoice;
    }
}
