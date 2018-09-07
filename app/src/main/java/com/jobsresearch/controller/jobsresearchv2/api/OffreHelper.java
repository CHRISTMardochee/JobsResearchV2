package com.jobsresearch.controller.jobsresearchv2.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jobsresearch.controller.jobsresearchv2.models.Offre;

public class OffreHelper {
    private static final String COLLECTION_NAME = "offres";

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getOffresCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---

    public static Task<Void> createOffre(String uid, String secteur,  String fonction ,String lieu,String type, String poste, String profil) {
        // 1 - Create User object
        Offre offreToCreate = new Offre(uid, secteur,fonction,lieu,type,poste,profil);
        // 2 - Add a new User Document to Firestore
       return OffreHelper.getOffresCollection()
                .document(uid) // Setting uID for Document
                .set(offreToCreate); // Setting object for Document
                /*
        return PublicationHelper.getPublicationCollection()
                .document(uid)
                .collection(COLLECTION_NAME)
                .add(offreToCreate);*/
    }

    //--Query--



    // --- GET ---

    public static Task<DocumentSnapshot> getOffre(String uid){
        return OffreHelper.getOffresCollection().document(uid).get();
    }

    // --- UPDATE ---

    public static Task<Void> updateOffre(String uid, String secteur,  String lieu,String fonction, String poste, String profil,String type) {
        return OffreHelper.getOffresCollection().document(uid).update("secteur", secteur,"lieu",lieu,"fonction",fonction,"poste",poste,"profil",profil,"type",type);
    }

    // --- DELETE ---

    public static Task<Void> deleteOffre(String uid) {
        return OffreHelper.getOffresCollection().document(uid).delete();
    }
}
