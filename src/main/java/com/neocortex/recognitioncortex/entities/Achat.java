package com.neocortex.recognitioncortex.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "achats")
public class Achat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length=50)
    private String nom;

    @NotNull
    private int nombre;

    @NotNull
    private int montant;

    //@NotNull
    //private String type;

    @ManyToOne
    @NotNull
    private Utilisateur utilisateur;
}
