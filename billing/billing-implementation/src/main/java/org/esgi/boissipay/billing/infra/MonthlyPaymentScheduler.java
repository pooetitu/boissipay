package org.esgi.boissipay.billing.infra;

import java.time.ZonedDateTime;

import org.esgi.boissipay.billing.domain.BillingRepository;
import org.springframework.scheduling.annotation.Scheduled;

public class MonthlyPaymentScheduler {
    private final BillingRepository billingRepository;

    public MonthlyPaymentScheduler(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    @Scheduled()
    public void createInvoice() {
        var bills = billingRepository.getBillOfLastMonth(ZonedDateTime.now());
        // TODO for each bills notify user concerned
    }
}
