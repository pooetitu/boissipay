package org.esgi.boissipay.contract;

import org.esgi.boissipay.contract.api.ContractsApiDelegate;
import org.esgi.boissipay.contract.kernel.ContractMapper;
import org.esgi.boissipay.contract.model.ContractRequest;
import org.esgi.boissipay.contract.model.ContractResponse;
import org.esgi.boissipay.contract.model.ContractsResponse;
import org.esgi.boissipay.contract.use_case.CreateContractUseCase;
import org.esgi.boissipay.contract.use_case.GetActiveContractsUseCase;
import org.esgi.boissipay.contract.use_case.GetContractByRefUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SpringContractsApiDelegate implements ContractsApiDelegate {

    private final CreateContractUseCase createContractUseCase;
    private final GetActiveContractsUseCase getActiveContractsUseCase;

    private final GetContractByRefUseCase getContractByRefUseCase;

    public SpringContractsApiDelegate(CreateContractUseCase createContractUseCase, GetActiveContractsUseCase getActiveContractsUseCase, GetContractByRefUseCase getContractByRefUseCase) {
        this.createContractUseCase = createContractUseCase;
        this.getActiveContractsUseCase = getActiveContractsUseCase;
        this.getContractByRefUseCase = getContractByRefUseCase;
    }


    @Override
    public ResponseEntity<ContractResponse> getContract(String contractRef) {
        var contract = getContractByRefUseCase.getContract(contractRef);
        if (contract == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ContractMapper.toContractResponse(contract));
    }

    @Override
    public ResponseEntity<ContractResponse> postContract(ContractRequest contractRequest) {
        createContractUseCase.createContract(contractRequest);
        return ResponseEntity.ok(new ContractResponse());
    }

    @Override
    public ResponseEntity<ContractsResponse> getContracts() {
        var contracts = getActiveContractsUseCase.getContracts();
        return ResponseEntity.ok(
            ContractMapper.toContractsResponse(contracts)
        );
    }
}
