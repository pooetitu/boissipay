package org.esgi.boissipay.billing.infra;

import org.esgi.boissipay.billing.domain.event.EventDispatcher;
import org.esgi.boissipay.billing.domain.models.Invoice;
import org.esgi.boissipay.billing.domain.repository.ContractRepository;
import org.esgi.boissipay.billing.domain.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Service
public final class MonthlyBillingScheduler {
    private static final Logger logger = LoggerFactory.getLogger(MonthlyBillingScheduler.class);
    private final PaymentRepository paymentRepository;

    private final ContractRepository contractRepository;
    private final EventDispatcher eventDispatcher;

    public MonthlyBillingScheduler(PaymentRepository paymentRepository, ContractRepository contractRepository, EventDispatcher eventDispatcher) {
        this.paymentRepository = paymentRepository;
        this.contractRepository = contractRepository;
        this.eventDispatcher = eventDispatcher;
    }

    // Every 2 minutes
    @Scheduled(cron = "${invoice.scheduling.cron}")
    public void createInvoice() {
        logger.info("Creating invoices");
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
