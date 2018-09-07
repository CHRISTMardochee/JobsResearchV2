package com.jobsresearch.controller.jobsresearchv2.api;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.jobsresearch.controller.jobsresearchv2.models.Message;
import com.jobsresearch.controller.jobsresearchv2.models.User;

public class MessageHelper {
    private static final String COLLECTION_NAME = "messages";

    // --- GET ---

    public static Query getAllMessageForChat(String chat){
        return ChatHelper.getChatCollection()
                .document(chat)
                .collection(COLLECTION_NAME)
                .orderBy("dateCreated")
                .limit(5);
    }


    public static Task<DocumentReference> createMessageForChat(String textMessage, String chat, User userSender){

        // 1 - Create the Message object
        Message message = new Message(textMessage, userSender);

        // 2 - Store Message to Firestore
        return ChatHelper.getChatCollection()
                .document(chat)
                .collection(COLLECTION_NAME)
                .add(message);
    }

    public static Task<DocumentReference> createMessageWithImageForChat(String urlImage, String textMessage, String chat, User userSender){

        // 1 - Creating Message with the URL image
        Message message = new Message(textMessage, urlImage, userSender);

        // 2 - Storing Message on Firestore
        return ChatHelper.getChatCollection()
                .document(chat)
                .collection(COLLECTION_NAME)
                .add(message);
    }


}
