package com.neocortex.recognitioncortex.repository;

import com.neocortex.recognitioncortex.entities.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
    List<AccountOperation> findByCortexAccountId(String accountId);
    Page<AccountOperation> findByCortexAccountIdOrderByOperationDateDesc(String accountId, Pageable pageable);
}
