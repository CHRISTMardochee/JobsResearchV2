package com.jobsresearch.controller.jobsresearchv2.models;

public class OffreFavoris {
    String uid;
    Offre offreFavoris;

    public OffreFavoris(){}

    public Offre getOffreFavoris() {
        return offreFavoris;
    }

    public void setOffreFavoris(String uid ,Offre offreFavoris) {
        this.offreFavoris = offreFavoris;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public OffreFavoris(String uid, Offre offreFavoris){

        this.uid=uid;
        this.offreFavoris=offreFavoris;
    }


}
