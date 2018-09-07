package com.jobsresearch.controller.jobsresearchv2.models;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Offre {
    String uid;
    String secteur;
    String fonction;
    String poste;
    String lieu;
    String type;
    String profil;
    Date dateCreated;

    public Offre(String uid, String secteur,String fonction,String lieu,String type, String poste, String profil){
        this.uid=uid;
        this.secteur=secteur;
        this.fonction=fonction;
        this.lieu=lieu;
        this.type=type;
        this.poste=poste;
        this.profil=profil;

    }

    //GETTERS

    @ServerTimestamp public Date getDateCreated() { return dateCreated; }

    public String getUid() {
        return uid;
    }
    public String getPoste() {
        return poste;
    }
    public String getLieu() {
        return lieu;
    }

    public String getSecteur() {
        return secteur;
    }


    public String getFonction() {
        return fonction;
    } public String getType() {
        return type;
    }

    public String getProfil() {
        return profil;
    }


    //SETTERS
    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public Offre(){
    }
}
