package com.example.pp_backend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class PpBackendApplication {

    public static void GetUsers(){
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collection = firestore.collection("users");
   collection.listDocuments().forEach(dr->{
       DocumentReference documentReference = firestore.document(dr.getPath());
       System.out.println("Document ID: "+ documentReference);

   });
        // Get all documents in the "users" collection

    }
    public static void main(String[] args) throws IOException {


        ClassLoader classLoader = PpBackendApplication.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("config.json")).getFile());
        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount)).setDatabaseUrl("https://default.firebaseio.com")
                        .build();
        try{
            FirebaseApp.initializeApp(options);
            GetUsers();

            System.out.println("Database connected");

        }catch (Exception E){
            System.out.println(E.getMessage());
        }
        SpringApplication.run(PpBackendApplication.class, args);
    }

}
