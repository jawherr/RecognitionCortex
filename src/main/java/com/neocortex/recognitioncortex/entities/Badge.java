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
@Table(name = "badges", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nom"
        })
})
public class Badge {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true,nullable = false,length = 50)
    private String nom;

    @ManyToOne
    @NotNull
    private Utilisateur image;

    @NotNull
    @Column(length=120)
    private String description;

    @ManyToOne
    @NotNull
    private Utilisateur utilisateur;
}
