package com.neocortex.recognitioncortex.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "calendrier")
public class Calendrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date date;

    @NotNull
    @Column(length=50)
    private String objet;

    @NotNull
    private String description;

    @OneToOne(mappedBy = "calendrier")
    private Utilisateur utilisateur;

    @OneToOne(mappedBy = "calendrier")
    private Equipe equipe;
}
