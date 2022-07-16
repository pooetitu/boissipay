package org.esgi.boissipay.contract.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JPAContractRepository extends JpaRepository<ContractEntity, String> {
    List<ContractEntity> findByStatusEquals(String status);

    Optional<ContractEntity> findByRef(String contractRef);
}
