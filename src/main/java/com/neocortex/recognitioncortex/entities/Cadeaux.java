package com.neocortex.recognitioncortex.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cadeaux", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nom"
        })
})
public class Cadeaux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull
    //private String type;

    @NotNull
    @Column(length=50)
    private String nom;

    @NotNull
    private int valeur;

    @ManyToOne
    @NotNull
    private Utilisateur utilisateur;
}
