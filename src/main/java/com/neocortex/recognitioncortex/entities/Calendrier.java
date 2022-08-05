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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Date date;

    @NotNull
    private String objet;

    @NotNull
    private String description;

    @OneToOne(mappedBy = "calendrier", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Utilisateur utilisateur;

    @OneToOne(mappedBy = "calendrier", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Equipe equipe;
}
