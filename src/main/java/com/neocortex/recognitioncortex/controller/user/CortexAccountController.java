package com.neocortex.recognitioncortex.controller.user;

import com.neocortex.recognitioncortex.dtos.*;
import com.neocortex.recognitioncortex.exception.BalanceNotSufficientException;
import com.neocortex.recognitioncortex.exception.CortexAccountNotFoundException;
import com.neocortex.recognitioncortex.service.CortexAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CortexAccountController {
    private CortexAccountService cortexAccountService;

    public CortexAccountController(CortexAccountService cortexAccountService) {
        this.cortexAccountService = cortexAccountService;
    }

    @GetMapping("/accounts/{accountId}")
    public CortexAccountDTO getCortexAccount(@PathVariable String accountId) throws CortexAccountNotFoundException {
        return cortexAccountService.getCortexAccount(accountId);
    }
    @GetMapping("/accounts")
    public List<CortexAccountDTO> listAccounts(){
        return cortexAccountService.cortexAccountList();
    }
    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return cortexAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name="page",defaultValue = "0") int page,
            @RequestParam(name="size",defaultValue = "5")int size) throws CortexAccountNotFoundException {
        return cortexAccountService.getAccountHistory(accountId,page,size);
    }
    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws CortexAccountNotFoundException, BalanceNotSufficientException {
        this.cortexAccountService.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
    }
    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws CortexAccountNotFoundException {
        this.cortexAccountService.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }
    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws CortexAccountNotFoundException, BalanceNotSufficientException {
        this.cortexAccountService.transfer(
                transferRequestDTO.getAccountSource(),
                transferRequestDTO.getAccountDestination(),
                transferRequestDTO.getAmount());
    }
}
