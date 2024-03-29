package org.esgi.boissipay.billing;


import org.esgi.boissipay.billing.api.InvoicesApiDelegate;
import org.esgi.boissipay.billing.kernel.InvoiceMapper;
import org.esgi.boissipay.billing.model.Invoice;
import org.esgi.boissipay.billing.model.InvoicesGet200Response;
import org.esgi.boissipay.billing.use_case.GetInvoiceByInvoiceRef;
import org.esgi.boissipay.billing.use_case.GetInvoiceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SpringInvoicesApiDelegate implements InvoicesApiDelegate {

    private final GetInvoiceUseCase getInvoiceUseCase;
    private final GetInvoiceByInvoiceRef getInvoiceByInvoiceRef;

    public SpringInvoicesApiDelegate(GetInvoiceUseCase getInvoiceUseCase,
                                     GetInvoiceByInvoiceRef getInvoiceByInvoiceRef) {
        this.getInvoiceUseCase = getInvoiceUseCase;
        this.getInvoiceByInvoiceRef = getInvoiceByInvoiceRef;
    }

    @Override
    public ResponseEntity<InvoicesGet200Response> invoicesGet() {
        var invoices = getInvoiceUseCase.searchInvoice();
        var response = new InvoicesGet200Response();
        response.setInvoices(invoices.stream().map(InvoiceMapper::toInvoiceResponse).toList());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Invoice> invoicesInvoiceRefGet(String invoiceRef) {
        var invoice = getInvoiceByInvoiceRef.getInvoice(invoiceRef);
        return ResponseEntity.ok(InvoiceMapper.toInvoiceResponse(invoice));
    }
}
