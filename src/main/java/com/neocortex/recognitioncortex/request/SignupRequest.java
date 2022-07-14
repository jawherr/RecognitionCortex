package com.neocortex.recognitioncortex.request;

import com.neocortex.recognitioncortex.entities.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class SignupRequest {

    @NotNull
    @Column(length=50)
    private String nom;

    @NotNull
    @Column(length=50)
    @Email
    private String email;

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
    private Solde solde;
    private Calendrier calendrier;
    private Collection<Equipe> equipes;
    private Collection<Badge> badges;
    private Collection<Monnaie> monnaies;
    private Collection<Achat> achats;
    private Collection<Publication> publications;
    private Collection<Employe_speciality> employe_specialities;
    private Collection<Recomponse> recomponses;
    private Collection<Cadeaux> cadeaux;
    private Collection<Message> messageries;
    private Collection<Notification> notifications;
    @NotNull
    @Column(length=50)
    private String username;

    private Set<String> role;

    @NotNull
    @Column(length=50)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Solde getSolde() {
        return solde;
    }

    public void setSolde(Solde solde) {
        this.solde = solde;
    }

    public Calendrier getCalendrier() {
        return calendrier;
    }

    public void setCalendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
    }

    public Collection<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(Collection<Equipe> equipes) {
        this.equipes = equipes;
    }

    public Collection<Badge> getBadges() {
        return badges;
    }

    public void setBadges(Collection<Badge> badges) {
        this.badges = badges;
    }

    public Collection<Monnaie> getMonnaies() {
        return monnaies;
    }

    public void setMonnaies(Collection<Monnaie> monnaies) {
        this.monnaies = monnaies;
    }

    public Collection<Achat> getAchats() {
        return achats;
    }

    public void setAchats(Collection<Achat> achats) {
        this.achats = achats;
    }

    public Collection<Publication> getPublications() {
        return publications;
    }

    public void setPublications(Collection<Publication> publications) {
        this.publications = publications;
    }

    public Collection<Employe_speciality> getEmploye_specialities() {
        return employe_specialities;
    }

    public void setEmploye_specialities(Collection<Employe_speciality> employe_specialities) {
        this.employe_specialities = employe_specialities;
    }

    public Collection<Recomponse> getRecomponses() {
        return recomponses;
    }

    public void setRecomponses(Collection<Recomponse> recomponses) {
        this.recomponses = recomponses;
    }

    public Collection<Cadeaux> getCadeaux() {
        return cadeaux;
    }

    public void setCadeaux(Collection<Cadeaux> cadeaux) {
        this.cadeaux = cadeaux;
    }

    public Collection<Message> getMessageries() {
        return messageries;
    }

    public void setMessageries(Collection<Message> messageries) {
        this.messageries = messageries;
    }

    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Collection<Notification> notifications) {
        this.notifications = notifications;
    }
}
