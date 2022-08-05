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
@Table(name = "recompenses", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nom"
        })
})
public class Recompense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private String image;

    @NotNull
    private String description;

    //@NotNull
    //private String type;

    @ManyToOne
    @NotNull
    private Utilisateur utilisateur;
}