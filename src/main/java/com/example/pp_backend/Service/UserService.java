package com.example.pp_backend.Service;

import com.example.pp_backend.Models.User;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    Firestore db;

    public UserService() {
        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId("mvp1996-c0b20")
                        .build();
        db = firestoreOptions.getService();
    }

    public boolean login(String email, String password) {
        try {
            // verify email and password in firestore database
            QuerySnapshot snapshot = db.collection("users").
                    whereEqualTo("email", email).
                    whereEqualTo("password", password)
                    .get()
                    .get();
            if (snapshot.isEmpty()) {
                return false;
            }
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean addUser(User u) {
        try {

            db.collection("users").add(u).get();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
