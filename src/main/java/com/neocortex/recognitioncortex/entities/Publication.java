package com.neocortex.recognitioncortex.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publications")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @NotNull
    private int nb_like;

    //@NotNull
    //private String type;

    @ManyToOne
    @NotNull
    private Utilisateur utilisateur;

    @NotNull
    @OneToMany(mappedBy="publication")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Commentaire> commentaires;
}
