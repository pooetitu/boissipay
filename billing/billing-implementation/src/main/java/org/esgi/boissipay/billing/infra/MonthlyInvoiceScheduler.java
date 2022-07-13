package org.esgi.boissipay.billing.infra;

import java.time.ZonedDateTime;

import org.esgi.boissipay.billing.domain.BillingRepository;
import org.springframework.scheduling.annotation.Scheduled;

public final class MonthlyInvoiceScheduler {
    private final BillingRepository billingRepository;

    public MonthlyInvoiceScheduler(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    @Scheduled()
    public void createInvoice() {
        var bills = billingRepository.getBillOfLastMonth(ZonedDateTime.now());
        // TODO for each bills notify user concerned

        // faire emergée une notion de payment et de bill
        // scheduler quand il faut
        // mettre les schemas des events a jour.
        // reception des events côte contract
        // mise en place d'une "clean archi côté contrat ?"
        // mise en place de redis (plus côté contrat)
        // notifier les utilisateurs
    }
}
