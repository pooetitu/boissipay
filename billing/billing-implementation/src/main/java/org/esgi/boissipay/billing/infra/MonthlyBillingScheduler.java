package org.esgi.boissipay.billing.infra;

import org.esgi.boissipay.billing.domain.models.Invoice;
import org.esgi.boissipay.billing.domain.repository.ContractRepository;
import org.esgi.boissipay.billing.domain.repository.PaymentRepository;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class MonthlyBillingScheduler {
    private final PaymentRepository paymentRepository;

    private final ContractRepository contractRepository;
    private final DefaultEventDispatcher eventDispatcher;

    public MonthlyBillingScheduler(PaymentRepository paymentRepository, ContractRepository contractRepository, DefaultEventDispatcher eventDispatcher) {
        this.paymentRepository = paymentRepository;
        this.contractRepository = contractRepository;
        this.eventDispatcher = eventDispatcher;
    }

    // Daily at 11am
    @Scheduled(cron = "0 0 11 * * *")
    public void createInvoice() {
        var invoices = new HashMap<String, Invoice>();
        var contracts = contractRepository.getActiveContracts();

        contracts.forEach(contract -> {
            var payments = paymentRepository.getPayedNotBilledPayments(contract.id());
            payments.forEach(
                payment -> {
                    var invoice = invoices.get(payment.operation().contactPerson().ccuid());
                    if (invoice == null) {
                        invoice = new Invoice(
                            UUID.randomUUID().toString(),
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

        invoices.values().forEach(eventDispatcher::dispatchSendInvoice);
    }
}
