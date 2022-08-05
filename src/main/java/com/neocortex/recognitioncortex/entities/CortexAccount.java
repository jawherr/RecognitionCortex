package com.neocortex.recognitioncortex.entities;

import com.neocortex.recognitioncortex.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 4)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CortexAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "cortexAccount",fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperations;

}
