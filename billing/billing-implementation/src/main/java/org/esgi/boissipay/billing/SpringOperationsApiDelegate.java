package org.esgi.boissipay.billing;


import org.esgi.boissipay.billing.api.OperationsApiDelegate;
import org.esgi.boissipay.billing.kernel.OperationMapper;
import org.esgi.boissipay.billing.model.OperationRequest;
import org.esgi.boissipay.billing.model.OperationResponse;
import org.esgi.boissipay.billing.model.OperationsGet200Response;
import org.esgi.boissipay.billing.model.OperationsResponse;
import org.esgi.boissipay.billing.use_case.GetOperationUseCase;
import org.esgi.boissipay.billing.use_case.GetOperationsByInvoiceRefUseCase;
import org.esgi.boissipay.billing.use_case.NewOperationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SpringOperationsApiDelegate implements OperationsApiDelegate {

    private final NewOperationUseCase newOperationUseCase;
    private final GetOperationUseCase getOperationUseCase;

    private final GetOperationsByInvoiceRefUseCase getOperationsByInvoiceRefUseCase;

    public SpringOperationsApiDelegate(NewOperationUseCase newOperationUseCase,
                                       GetOperationUseCase getOperationUseCase,
                                       GetOperationsByInvoiceRefUseCase getOperationsByInvoiceRefUseCase) {
        this.newOperationUseCase = newOperationUseCase;
        this.getOperationUseCase = getOperationUseCase;
        this.getOperationsByInvoiceRefUseCase = getOperationsByInvoiceRefUseCase;
    }

    @Override
    public ResponseEntity<OperationsGet200Response> operationsGet(String invoiceRef) {
        var response = new OperationsGet200Response();
        var operations = getOperationsByInvoiceRefUseCase.getOperations(invoiceRef);
        var operationsResponse = new OperationsResponse();
        operationsResponse.setOperations(operations.stream().map(OperationMapper::toOperationResponse).toList());
        response.addOrdersItem(operationsResponse);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<OperationResponse> operationsPost(OperationRequest operationRequest) {
        String operationId = newOperationUseCase.newOperation(OperationMapper.toOperation(operationRequest));
        var operation = getOperationUseCase.getOperation(operationId);
        return ResponseEntity.ok(OperationMapper.toOperationResponse(operation));
    }
}
