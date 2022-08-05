package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.dtos.*;
import com.neocortex.recognitioncortex.exception.BalanceNotSufficientException;
import com.neocortex.recognitioncortex.exception.CortexAccountNotFoundException;
import com.neocortex.recognitioncortex.exception.UserNotFoundException;

import java.util.List;

public interface CortexAccountService {
    UtilisateurDTO saveUser(UtilisateurDTO utilisateurDTO);
    CurrentCortexAccountDTO saveCurrentCortexAccount(double initialBalance, double overDraft, Long utilisateurId) throws UserNotFoundException;
    SavingCortexAccountDTO saveSavingCortexAccount(double initialBalance, double interestRate, Long userId) throws UserNotFoundException;
    List<UtilisateurDTO> listUsers();
    CortexAccountDTO getCortexAccount(String accountId) throws CortexAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws CortexAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws CortexAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws CortexAccountNotFoundException, BalanceNotSufficientException;

    List<CortexAccountDTO> cortexAccountList();

    UtilisateurDTO getUser(Long userId) throws UserNotFoundException;

    UtilisateurDTO updateUser(UtilisateurDTO userDTO);

    void deleteUser(Long userId);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws CortexAccountNotFoundException;

    List<UtilisateurDTO> searchUsers(String keyword);
}
