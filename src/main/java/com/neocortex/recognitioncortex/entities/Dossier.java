package com.neocortex.recognitioncortex.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dossiers")
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date date;

    @NotNull
    private String remarque;

    @NotNull
    private String description;

    @OneToOne(mappedBy = "dossier", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Equipe equipe;

    @NotNull
    @OneToMany(mappedBy="dossier")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Tache> taches;
}
