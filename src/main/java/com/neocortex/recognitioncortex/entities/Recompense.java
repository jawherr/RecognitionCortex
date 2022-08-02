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
    @Column(length=50)
    private String nom;

    @NotNull
    private int valuer;

    //@NotNull
    //private String type;

    @ManyToOne
    @NotNull
    private Utilisateur utilisateur;
}
