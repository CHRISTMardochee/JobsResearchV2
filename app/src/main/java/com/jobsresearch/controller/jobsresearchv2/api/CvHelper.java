package com.jobsresearch.controller.jobsresearchv2.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jobsresearch.controller.jobsresearchv2.models.Cv;

public class CvHelper {
    private static final String COLLECTION_NAME = "cv";

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getCvCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---

    public static Task<Void> createCv(String uid, String nom, String email, String adresse, String telephone, String objectif, String formation, String competence, String cdi) {
        // 1 - Create User object
        Cv cvToCreate = new Cv(uid, nom, email,adresse,telephone,objectif,formation,competence,cdi);
        // 2 - Add a new User Document to Firestore
        return CvHelper.getCvCollection()
                .document(uid) // Setting uID for Document
                .set(cvToCreate); // Setting object for Document
    }

    // --- GET ---

    public static Task<DocumentSnapshot> getCv(String uid){
        return CvHelper.getCvCollection().document(uid).get();
    }

    // --- UPDATE ---

    public static Task<Void> updateCv(String uid, String nom, String email, String adresse, String telephone, String objectif, String formation, String competence, String cdi) {
        return CvHelper.getCvCollection().document(uid).update("nom", nom,"email",email,"adresse",adresse,"telephone",telephone,"objectif",objectif,"formation",formation,"competence",competence,"cdi",cdi);
    }

    // --- DELETE ---

    public static Task<Void> deleteCv(String uid) {
        return CvHelper.getCvCollection().document(uid).delete();
    }
}
