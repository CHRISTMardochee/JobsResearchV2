package com.jobsresearch.controller.jobsresearchv2.models;

public class Cv {
    private String uid;
    private String nom;
    private String email;
    private String adresse;
    private String telephone;
    private String objectif;
    private String formation;
    private String competence;
    private String cdi;

    public Cv() { }

    public Cv(String uid, String nom, String email, String adresse, String cdi, String telephone, String objectif, String formation, String competence){
        this.uid = uid;
        this.nom = nom;
        this.email = email;
        this.adresse=adresse;
        this.cdi=cdi;
        this.telephone=telephone;
        this.objectif=objectif;
        this.formation=formation;
        this.competence=competence;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getCdi() {
        return cdi;
    }

    public void setCdi(String cdi) {
        this.cdi = cdi;
    }



}
