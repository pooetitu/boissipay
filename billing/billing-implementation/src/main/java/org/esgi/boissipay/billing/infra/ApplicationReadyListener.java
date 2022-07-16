package org.esgi.boissipay.billing.infra;

import org.esgi.boissipay.billing.domain.event.CreateContractHandler;
import org.esgi.boissipay.billing.domain.event.EventDispatcher;
import org.esgi.boissipay.billing.domain.event.InvoiceSentHandler;
import org.esgi.boissipay.billing.domain.event.NewOperationHandler;
import org.esgi.boissipay.billing.domain.event.PaymentSuccessHandler;
import org.esgi.boissipay.billing.domain.event.ProcessPaymentHandler;
import org.esgi.boissipay.billing.domain.event.SendInvoiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyListener.class);
    private final Set<CreateContractHandler> createContractHandlers;
    private final Set<InvoiceSentHandler> invoiceSentHandlers;
    private final Set<PaymentSuccessHandler> paymentSuccessHandlers;
    private final Set<ProcessPaymentHandler> processPaymentHandlers;
    private final Set<SendInvoiceHandler> sendInvoiceHandlers;
    private final Set<NewOperationHandler> newOperationHandlers;
    private final EventDispatcher eventDispatcher;

    public ApplicationReadyListener(Set<CreateContractHandler> createContractHandlers,
                                    Set<InvoiceSentHandler> invoiceSentHandlers,
                                    Set<PaymentSuccessHandler> paymentSuccessHandlers,
                                    Set<ProcessPaymentHandler> processPaymentHandlers,
                                    Set<SendInvoiceHandler> sendInvoiceHandlers,
                                    Set<NewOperationHandler> newOperationHandlers,
                                    EventDispatcher eventDispatcher) {
        this.createContractHandlers = createContractHandlers;
        this.invoiceSentHandlers = invoiceSentHandlers;
        this.paymentSuccessHandlers = paymentSuccessHandlers;
        this.processPaymentHandlers = processPaymentHandlers;
        this.sendInvoiceHandlers = sendInvoiceHandlers;
        this.newOperationHandlers = newOperationHandlers;
        this.eventDispatcher = eventDispatcher;
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        LOGGER.info("Application is ready, filling event dispatcher");
        eventDispatcher
            .setCreateContractHandlers(createContractHandlers)
            .setInvoiceSentHandlers(invoiceSentHandlers)
            .setPaymentSuccessHandlers(paymentSuccessHandlers)
            .setProcessPaymentHandlers(processPaymentHandlers)
            .setSendInvoiceHandlers(sendInvoiceHandlers)
            .setNewOperationHandlers(newOperationHandlers);
    }
}
