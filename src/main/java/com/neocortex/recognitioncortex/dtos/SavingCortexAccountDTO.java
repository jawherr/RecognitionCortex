package com.neocortex.recognitioncortex.dtos;

import com.neocortex.recognitioncortex.enums.AccountStatus;
import lombok.Data;
import java.util.Date;

@Data
public class SavingCortexAccountDTO extends CortexAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private UtilisateurDTO utilisateurDTO;
    private double interestRate;
}
