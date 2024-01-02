package com.example.pp_backend.Service;

import com.example.pp_backend.Models.User;
import com.example.pp_backend.PpBackendApplication;
import com.example.pp_backend.Responses.LoginResponse;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Service
public class UserService {

 Firestore db ;
    public UserService() throws IOException {
        try{
        ClassLoader classLoader = PpBackendApplication.class.getClassLoader();
        URL resource = classLoader.getResource("./config.json");
        if(resource == null){
            throw new FileNotFoundException("File not found");
        }
        File file = new File(Objects.requireNonNull(classLoader.getResource("./config.json")).getFile());
        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount)).setDatabaseUrl("https://default.firebaseio.com")
                .build();

            FirebaseApp.initializeApp(options);
              db = FirestoreClient.getFirestore();
              System.out.println("Database connected");



        }catch (Exception E){
            System.out.println(E.getMessage()+"HELLo");
            throw  E ;
        }

    }

    public LoginResponse login(String email, String password) {
        try {
            // verify email and password in firestore database
            QuerySnapshot snapshot = db.collection("users").
                    whereEqualTo("email", email).
                    whereEqualTo("password", password)
                    .get()
                    .get();
            if (snapshot.isEmpty()) {
                return new LoginResponse(false,401);
            }
            return new LoginResponse(true,200);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new LoginResponse(false,500);
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
