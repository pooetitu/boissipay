package org.esgi.boissipay.billing.use_case;

import org.esgi.boissipay.billing.domain.models.Invoice;
import org.esgi.boissipay.billing.domain.repository.ContractRepository;
import org.esgi.boissipay.billing.domain.repository.PaymentRepository;

import java.time.ZonedDateTime;

public class GetInvoiceByInvoiceRef {
    private final PaymentRepository paymentRepository;
    private final ContractRepository contractRepository;

    public GetInvoiceByInvoiceRef(PaymentRepository paymentRepository, ContractRepository contractRepository) {
        this.paymentRepository = paymentRepository;
        this.contractRepository = contractRepository;
    }

    public Invoice getInvoice(String invoiceRef) {
        var payments = paymentRepository.getByInvoiceRef(invoiceRef);
        if (payments.isEmpty()) {
            throw new IllegalArgumentException("No invoice found for invoiceRef " + invoiceRef);
        }
        var contract = contractRepository.getContract(payments.get(0).operation().contractId());
        return new Invoice(
            invoiceRef,
            contract.ref(),
            contract.id(),
            payments.get(0).operation().contactPerson(),
            ZonedDateTime.now(),
            payments
        );
    }
}
