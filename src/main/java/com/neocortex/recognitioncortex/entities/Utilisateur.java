package com.neocortex.recognitioncortex.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utilisateurs", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @NotNull
    @Column(length=50)
    @Email
    private String email;

    @Column(length=120)
    @NotNull
    private String password;

    @NotNull
    @Column(length=50)
    private String nom;

    @NotNull
    @Column(length=50)
    private String username;

    @NotNull
    private Date date_naissance;

    @NotNull
    @Column(length=120)
    private String address;

    @NotNull
    private Date created_at;

    @NotNull
    private Date updated_at;

    private String cover_image;

    @Column(length=120)
    private String brief;

    @ManyToMany
    private Collection<Role> roles;

    @OneToOne
    private Solde solde;

    @OneToOne
    private Calendrier calendrier;

    @NotNull
    @OneToMany(mappedBy="utilisateur")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Equipe> equipes;

    @NotNull
    @OneToMany(mappedBy="utilisateur")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Badge> badges;

    @NotNull
    @OneToMany(mappedBy="utilisateur")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Monnaie> monnaies;

    @NotNull
    @OneToMany(mappedBy="utilisateur")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Achat> achats;

    @NotNull
    @OneToMany(mappedBy="utilisateur")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Publication> publications;

    @NotNull
    @OneToMany(mappedBy="utilisateur")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Employe_speciality> employe_specialities;

    @NotNull
    @OneToMany(mappedBy="utilisateur")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Recompense> Recompenses;

    @NotNull
    @OneToMany(mappedBy="utilisateur")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Cadeaux> cadeaux;

    @NotNull
    @OneToMany(mappedBy="utilisateur")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Message> messageries;

    @NotNull
    @OneToMany(mappedBy="utilisateur")
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private Collection<Notification> notifications;

    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Utilisateur(String email, String password, String nom,
                       String username, Date date_naissance,
                       String address, Date created_at, Date updated_at) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.username = username;
        this.date_naissance = date_naissance;
        this.address = address;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
