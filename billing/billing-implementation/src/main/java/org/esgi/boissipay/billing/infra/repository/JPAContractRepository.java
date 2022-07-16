package org.esgi.boissipay.billing.infra.repository;

import org.esgi.boissipay.billing.infra.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAContractRepository extends JpaRepository<ContractEntity, String> {
    List<ContractEntity> findByStatusEquals(String status);
}
