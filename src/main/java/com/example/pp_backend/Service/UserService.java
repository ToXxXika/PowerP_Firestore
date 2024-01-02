package com.example.pp_backend.Service;

import com.example.pp_backend.Models.User;
import com.example.pp_backend.PpBackendApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@Service
public class UserService {
    Firestore db;

    public UserService() throws IOException {
        ClassLoader classLoader = PpBackendApplication.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("config.json")).getFile());
        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount)).setDatabaseUrl("https://default.firebaseio.com")
                .build();
        try{
            FirebaseApp.initializeApp(options);
            FirestoreOptions firestoreOptions =
                    FirestoreOptions.getDefaultInstance().toBuilder()
                            .setProjectId("mvp1996-c0b20")
                            .build();
            db = firestoreOptions.getService();
            System.out.println("Database connected");

        }catch (Exception E){
            System.out.println(E.getMessage());
        }

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
