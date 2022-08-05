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
@Table(name = "employe_specialities", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "titre"
        })
})
public class Employe_speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titre;

    @NotNull
    private String description;

    @ManyToOne
    @NotNull
    private Utilisateur utilisateur;
}
