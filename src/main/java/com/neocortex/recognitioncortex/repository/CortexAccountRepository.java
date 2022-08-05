package com.neocortex.recognitioncortex.repository;

import com.neocortex.recognitioncortex.entities.CortexAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CortexAccountRepository extends JpaRepository<CortexAccount,String> {
}
