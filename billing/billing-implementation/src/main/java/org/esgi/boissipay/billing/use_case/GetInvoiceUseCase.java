package org.esgi.boissipay.billing.use_case;

import org.esgi.boissipay.billing.domain.models.Invoice;
import org.esgi.boissipay.billing.domain.repository.ContractRepository;
import org.esgi.boissipay.billing.domain.repository.PaymentRepository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GetInvoiceUseCase {

    private final PaymentRepository paymentRepository;
    private final ContractRepository contractRepository;

    public GetInvoiceUseCase(PaymentRepository paymentRepository, ContractRepository contractRepository) {
        this.paymentRepository = paymentRepository;
        this.contractRepository = contractRepository;
    }

    public List<Invoice> searchInvoice() {
        var invoices = new HashMap<String, Invoice>();
        var contracts = contractRepository.getActiveContracts();

        contracts.forEach(contract -> {
            var payments = paymentRepository.getGetPayedAndBilledPayments(contract.id());
            payments.forEach(
                payment -> {
                    var invoice = invoices.get(payment.operation().contactPerson().ccuid());
                    if (invoice == null) {
                        invoice = new Invoice(
                            payment.invoiceRef(),
                            contract.ref(),
                            contract.id(),
                            payment.operation().contactPerson(),
                            ZonedDateTime.now(),
                            new ArrayList<>()
                        );
                        invoices.put(payment.operation().contactPerson().ccuid(), invoice);
                    }
                    invoice.payments().add(payment);
                }
            );
        });
        return invoices.values().stream().toList();
    }
}
