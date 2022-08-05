package com.neocortex.recognitioncortex.service.impl;

import com.neocortex.recognitioncortex.entities.CortexAccount;
import com.neocortex.recognitioncortex.entities.CurrentAccount;
import com.neocortex.recognitioncortex.entities.SavingAccount;
import com.neocortex.recognitioncortex.repository.CortexAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CortexService {
    @Autowired
    private CortexAccountRepository cortexAccountRepository;
    public void consulter(){
        CortexAccount cortexAccount=
                cortexAccountRepository.findById("0b36be78-8d5d-446b-9f20-37eadc9d3c3b").orElse(null);
        if(cortexAccount!=null) {
            System.out.println("*****************************");
            System.out.println(cortexAccount.getId());
            System.out.println(cortexAccount.getBalance());
            System.out.println(cortexAccount.getStatus());
            System.out.println(cortexAccount.getCreatedAt());
            System.out.println(cortexAccount.getUtilisateur().getUsername());
            System.out.println(cortexAccount.getClass().getSimpleName());
            if (cortexAccount instanceof CurrentAccount) {
                System.out.println("Over Draft=>" + ((CurrentAccount) cortexAccount).getOverDraft());
            } else if (cortexAccount instanceof SavingAccount) {
                System.out.println("Rate=>" + ((SavingAccount) cortexAccount).getInterestRate());
            }
            cortexAccount.getAccountOperations().forEach(op -> {
                System.out.println(op.getType() + "\t" + op.getOperationDate() + "\t" + op.getAmount());
            });
        }
    }
}
