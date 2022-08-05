package com.neocortex.recognitioncortex.mappers;

import com.neocortex.recognitioncortex.dtos.AccountOperationDTO;
import com.neocortex.recognitioncortex.dtos.CurrentCortexAccountDTO;
import com.neocortex.recognitioncortex.dtos.SavingCortexAccountDTO;
import com.neocortex.recognitioncortex.dtos.UtilisateurDTO;
import com.neocortex.recognitioncortex.entities.AccountOperation;
import com.neocortex.recognitioncortex.entities.CurrentAccount;
import com.neocortex.recognitioncortex.entities.SavingAccount;
import com.neocortex.recognitioncortex.entities.Utilisateur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CortexAccountMapperImpl {
    public UtilisateurDTO fromUtilisateur(Utilisateur utilisateur){
        UtilisateurDTO utilisateurDTO=new UtilisateurDTO();
        BeanUtils.copyProperties(utilisateur,utilisateurDTO);
        return  utilisateurDTO;
    }
    public Utilisateur fromUtilisateurDTO(UtilisateurDTO utilisateurDTO){
        Utilisateur utilisateur=new Utilisateur();
        BeanUtils.copyProperties(utilisateurDTO,utilisateur);
        return  utilisateur;
    }

    public SavingCortexAccountDTO fromSavingCortexAccount(SavingAccount savingAccount){
        SavingCortexAccountDTO savingCortexAccountDTO=new SavingCortexAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingCortexAccountDTO);
        savingCortexAccountDTO.setUtilisateurDTO(fromUtilisateur(savingAccount.getUtilisateur()));
        savingCortexAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingCortexAccountDTO;
    }

    public SavingAccount fromSavingCortexAccountDTO(SavingCortexAccountDTO savingCortexAccountDTO){
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties(savingCortexAccountDTO,savingAccount);
        savingAccount.setUtilisateur(fromUtilisateurDTO(savingCortexAccountDTO.getUtilisateurDTO()));
        return savingAccount;
    }

    public CurrentCortexAccountDTO fromCurrentCortexAccount(CurrentAccount currentAccount){
        CurrentCortexAccountDTO currentCortexAccountDTO=new CurrentCortexAccountDTO();
        BeanUtils.copyProperties(currentAccount,currentCortexAccountDTO);
        currentCortexAccountDTO.setUtilisateurDTO(fromUtilisateur(currentAccount.getUtilisateur()));
        currentCortexAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentCortexAccountDTO;
    }

    public CurrentAccount fromCurrentCortexAccountDTO(CurrentCortexAccountDTO currentCortexAccountDTO){
        CurrentAccount currentAccount=new CurrentAccount();
        BeanUtils.copyProperties(currentCortexAccountDTO,currentAccount);
        currentAccount.setUtilisateur(fromUtilisateurDTO(currentCortexAccountDTO.getUtilisateurDTO()));
        return currentAccount;
    }

    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDTO accountOperationDTO=new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation,accountOperationDTO);
        return accountOperationDTO;
    }

}
