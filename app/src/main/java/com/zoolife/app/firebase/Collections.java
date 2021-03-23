package com.zoolife.app.firebase;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.zoolife.app.firebase.models.*;

public class Collections {

    public static CollectionReference groupsRef = FirebaseFirestore.getInstance().collection(Group.CLASS_NAME);
    public static CollectionReference usersRef = FirebaseFirestore.getInstance().collection(User.CLASS_NAME);

    public static StorageReference storageRef = FirebaseStorage.getInstance().getReference();

//    public static DatabaseReference statusRef = FirebaseDatabase.getInstance().getReference("status");

    /* Legacy
    public static CollectionReference chatRef = FirebaseFirestore.getInstance().collection(Chat.CLASS_NAME);
    public static CollectionReference userRef = FirebaseFirestore.getInstance().collection(User_legacy.CLASS_NAME);
     */

}
