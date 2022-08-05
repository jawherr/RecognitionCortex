package com.neocortex.recognitioncortex.service.impl;

import com.neocortex.recognitioncortex.dtos.*;
import com.neocortex.recognitioncortex.entities.*;
import com.neocortex.recognitioncortex.enums.OperationType;
import com.neocortex.recognitioncortex.exception.BalanceNotSufficientException;
import com.neocortex.recognitioncortex.exception.CortexAccountNotFoundException;
import com.neocortex.recognitioncortex.exception.UserNotFoundException;
import com.neocortex.recognitioncortex.mappers.CortexAccountMapperImpl;
import com.neocortex.recognitioncortex.repository.AccountOperationRepository;
import com.neocortex.recognitioncortex.repository.CortexAccountRepository;
import com.neocortex.recognitioncortex.repository.UtilisateurRepository;
import com.neocortex.recognitioncortex.service.CortexAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CortexAccountServiceImpl implements CortexAccountService {
    private UtilisateurRepository utilisateurRepository;
    private CortexAccountRepository cortexAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private CortexAccountMapperImpl dtoMapper;

    @Override
    public UtilisateurDTO saveUser(UtilisateurDTO utilisateurDTO) {
        log.info("Saving new Utilisateur");
        Utilisateur utilisateur=dtoMapper.fromUtilisateurDTO(utilisateurDTO);
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        return dtoMapper.fromUtilisateur(savedUtilisateur);
    }

    @Override
    public CurrentCortexAccountDTO saveCurrentCortexAccount(double initialBalance, double overDraft, Long userId) throws UserNotFoundException {
        Utilisateur utilisateur=utilisateurRepository.findById(userId).orElse(null);
        if(utilisateur==null)
            throw new UserNotFoundException("User not found");
        CurrentAccount currentAccount=new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setUtilisateur(utilisateur);
        CurrentAccount savedCortexAccount = cortexAccountRepository.save(currentAccount);
        return dtoMapper.fromCurrentCortexAccount(savedCortexAccount);
    }

    @Override
    public SavingCortexAccountDTO saveSavingCortexAccount(double initialBalance, double interestRate, Long userId) throws UserNotFoundException {
        Utilisateur utilisateur=utilisateurRepository.findById(userId).orElse(null);
        if(utilisateur==null)
            throw new UserNotFoundException("Utilisateur not found");
        SavingAccount savingAccount=new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setUtilisateur(utilisateur);
        SavingAccount savedCortexAccount = cortexAccountRepository.save(savingAccount);
        return dtoMapper.fromSavingCortexAccount(savedCortexAccount);
    }

    @Override
    public List<UtilisateurDTO> listUsers() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        List<UtilisateurDTO> usersDTOS = utilisateurs.stream()
                .map(utilisateur -> dtoMapper.fromUtilisateur(utilisateur))
                .collect(Collectors.toList());
        /*
        List<CustomerDTO> customerDTOS=new ArrayList<>();
        for (Customer customer:users){
            CustomerDTO customerDTO=dtoMapper.fromCustomer(customer);
            customerDTOS.add(customerDTO);
        }
        *
         */
        return usersDTOS;
    }

    @Override
    public CortexAccountDTO getCortexAccount(String accountId) throws CortexAccountNotFoundException {
        CortexAccount cortexAccount= cortexAccountRepository.findById(accountId)
                .orElseThrow(()->new CortexAccountNotFoundException("CortexAccount not found"));
        if(cortexAccount instanceof SavingAccount){
            SavingAccount savingAccount= (SavingAccount) cortexAccount;
            return dtoMapper.fromSavingCortexAccount(savingAccount);
        } else {
            CurrentAccount currentAccount= (CurrentAccount) cortexAccount;
            return dtoMapper.fromCurrentCortexAccount(currentAccount);
        }
    }

    @Override
    public void debit(String accountId, double amount, String description) throws CortexAccountNotFoundException, BalanceNotSufficientException {
        CortexAccount cortexAccount=cortexAccountRepository.findById(accountId)
                .orElseThrow(()->new CortexAccountNotFoundException("CortexAccount not found"));
        if(cortexAccount.getBalance()<amount)
            throw new BalanceNotSufficientException("Balance not sufficient");
        AccountOperation accountOperation=new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setCortexAccount(cortexAccount);
        accountOperationRepository.save(accountOperation);
        cortexAccount.setBalance(cortexAccount.getBalance()-amount);
        cortexAccountRepository.save(cortexAccount);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws CortexAccountNotFoundException {
        CortexAccount cortexAccount=cortexAccountRepository.findById(accountId)
                .orElseThrow(()->new CortexAccountNotFoundException("CortexAccount not found"));
        AccountOperation accountOperation=new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setCortexAccount(cortexAccount);
        accountOperationRepository.save(accountOperation);
        cortexAccount.setBalance(cortexAccount.getBalance()+amount);
        cortexAccountRepository.save(cortexAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws CortexAccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource,amount,"Transfer to "+accountIdDestination);
        credit(accountIdDestination,amount,"Transfer from "+accountIdSource);
    }
    @Override
    public List<CortexAccountDTO> cortexAccountList(){
        List<CortexAccount> cortexAccounts = cortexAccountRepository.findAll();
        List<CortexAccountDTO> cortexAccountDTOS = cortexAccounts.stream().map(cortexAccount -> {
            if (cortexAccount instanceof SavingAccount) {
                SavingAccount savingAccount = (SavingAccount) cortexAccount;
                return dtoMapper.fromSavingCortexAccount(savingAccount);
            } else {
                CurrentAccount currentAccount = (CurrentAccount) cortexAccount;
                return dtoMapper.fromCurrentCortexAccount(currentAccount);
            }
        }).collect(Collectors.toList());
        return cortexAccountDTOS;
    }
    @Override
    public UtilisateurDTO getUser(Long userId) throws UserNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur Not found"));
        return dtoMapper.fromUtilisateur(utilisateur);
    }

    @Override
    public UtilisateurDTO updateUser(UtilisateurDTO utilisateurDTO) {
        log.info("Saving new Utilisateur");
        Utilisateur utilisateur=dtoMapper.fromUtilisateurDTO(utilisateurDTO);
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        return dtoMapper.fromUtilisateur(savedUtilisateur);
    }
    @Override
    public void deleteUser(Long userId){
        utilisateurRepository.deleteById(userId);
    }
    @Override
    public List<AccountOperationDTO> accountHistory(String accountId){
        List<AccountOperation> accountOperations = accountOperationRepository.findByCortexAccountId(accountId);
        return accountOperations.stream().map(op->dtoMapper.fromAccountOperation(op)).collect(Collectors.toList());
    }

    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws CortexAccountNotFoundException {
        CortexAccount cortexAccount=cortexAccountRepository.findById(accountId).orElse(null);
        if(cortexAccount==null) throw new CortexAccountNotFoundException("Account not Found");
        Page<AccountOperation> accountOperations = accountOperationRepository.findByCortexAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO=new AccountHistoryDTO();
        List<AccountOperationDTO> accountOperationDTOS = accountOperations.getContent().stream().map(op -> dtoMapper.fromAccountOperation(op)).collect(Collectors.toList());
        accountHistoryDTO.setAccountOperationDTOS(accountOperationDTOS);
        accountHistoryDTO.setAccountId(cortexAccount.getId());
        accountHistoryDTO.setBalance(cortexAccount.getBalance());
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
        return accountHistoryDTO;
    }

    @Override
    public List<UtilisateurDTO> searchUsers(String keyword) {
        List<Utilisateur> utilisateurs=utilisateurRepository.searchUser(keyword);
        List<UtilisateurDTO> utilisateurDTOS = utilisateurs.stream().map(cust -> dtoMapper.fromUtilisateur(cust)).collect(Collectors.toList());
        return utilisateurDTOS;
    }
}
