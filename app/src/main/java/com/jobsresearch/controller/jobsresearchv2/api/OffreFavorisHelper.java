package com.jobsresearch.controller.jobsresearchv2.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jobsresearch.controller.jobsresearchv2.models.Offre;
import com.jobsresearch.controller.jobsresearchv2.models.OffreFavoris;

public class OffreFavorisHelper {

    private static final String COLLECTION_NAME = "offresfavoris";

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getFavorisCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---

    public static Task<Void> createCanditature(String uid , Offre offre) {
        // 1 - Create User object
        OffreFavoris favorisToCreate = new OffreFavoris();
        // 2 - Add a new User Document to Firestore
        return OffreFavorisHelper.getFavorisCollection()
                .document(uid)
                .set(favorisToCreate);// Setting object for Document
    }

    // --- GET ---

    public static Task<DocumentSnapshot> getFavoris(String uid){
        return OffreFavorisHelper.getFavorisCollection().document(uid).get();
    }
}
