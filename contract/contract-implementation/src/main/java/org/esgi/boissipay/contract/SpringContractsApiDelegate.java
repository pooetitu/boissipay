package org.esgi.boissipay.contract;

import org.esgi.boissipay.contract.api.ContractsApiDelegate;
import org.esgi.boissipay.contract.model.ContractActionRequest;
import org.esgi.boissipay.contract.model.ContractRequest;
import org.esgi.boissipay.contract.model.ContractResponse;
import org.esgi.boissipay.contract.model.ContractsResponse;
import org.esgi.boissipay.contract.use_case.CreateContractUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SpringContractsApiDelegate implements ContractsApiDelegate {

    private final CreateContractUseCase createContractUseCase;

    public SpringContractsApiDelegate(CreateContractUseCase createContractUseCase) {
        this.createContractUseCase = createContractUseCase;
    }


    @Override
    public ResponseEntity<ContractResponse> getContract(String contractRef) {
        return ContractsApiDelegate.super.getContract(contractRef);
    }

    @Override
    public ResponseEntity<Void> patchContract(String contractRef, ContractActionRequest contractActionRequest) {
        return ContractsApiDelegate.super.patchContract(contractRef, contractActionRequest);
    }

    @Override
    public ResponseEntity<ContractResponse> postContract(ContractRequest contractRequest) {
        createContractUseCase.createContract(contractRequest);
        return ResponseEntity.ok(new ContractResponse());
    }

    @Override
    public ResponseEntity<ContractsResponse> searchContracts(String subscriberRef, String contractStatus, Integer limit, Integer offset) {
        return ContractsApiDelegate.super.searchContracts(subscriberRef, contractStatus, limit, offset);
    }
}
