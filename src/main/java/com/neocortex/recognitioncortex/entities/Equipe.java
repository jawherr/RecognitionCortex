package com.neocortex.recognitioncortex.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Collection;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nom"
        })
})
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length=50)
    private String nom;

    @NotNull
    @Column(length=50)
    private String objectif;

    @ManyToOne
    @NotNull
    private Utilisateur utilisateur;

    @NotNull
    @OneToMany(mappedBy="equipe")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Monnaie> monnaies;

    @OneToOne
    private Calendrier calendrier;

    @OneToOne
    private Dossier dossier;
}
