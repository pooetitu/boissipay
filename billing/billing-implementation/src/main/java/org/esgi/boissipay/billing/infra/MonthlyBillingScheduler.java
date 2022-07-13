package org.esgi.boissipay.billing.infra;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import org.esgi.boissipay.billing.domain.Invoice;
import org.esgi.boissipay.billing.domain.PaymentRepository;
import org.esgi.boissipay.billing.use_case.ProcessPaymentUseCase;
import org.springframework.scheduling.annotation.Scheduled;

public final class MonthlyBillingScheduler {
    private final PaymentRepository paymentRepository;
    private final ProcessPaymentUseCase processPaymentUseCase;
    private final EventDispatcher eventDispatcher;

    public MonthlyBillingScheduler(PaymentRepository paymentRepository, ProcessPaymentUseCase processPaymentUseCase, EventDispatcher eventDispatcher) {
        this.paymentRepository = paymentRepository;
        this.processPaymentUseCase = processPaymentUseCase;
        this.eventDispatcher = eventDispatcher;
    }

    @Scheduled()
    public void createInvoice() {
        var invoices = new HashMap<String, Invoice>();
        var payments = paymentRepository.getUnpaidPayments();
        payments.forEach(payment -> {
            processPaymentUseCase.pay(payment);
            var invoice = invoices.get(payment.contractName());
            if (invoice == null) {
                invoice = new Invoice(payment.contractName(), payment.contactPerson(), ZonedDateTime.now(), new ArrayList<>());
                invoices.put(payment.contractName(), invoice);
            } else {
                invoice.payments().add(payment);
            }
        });
        invoices.values().forEach(eventDispatcher::dispatchCreateInvoice);

        // faire emergée une notion de payment et de bill
        // scheduler quand il faut
        // mettre les schemas des events a jour.
        // reception des events côte contract
        // mise en place d'une "clean archi côté contrat ?"
        // mise en place de redis (plus côté contrat)
        // notifier les utilisateurs
    }
}
