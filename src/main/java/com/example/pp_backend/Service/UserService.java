package com.example.pp_backend.Service;

import com.example.pp_backend.Models.User;
import com.example.pp_backend.PpBackendApplication;
import com.example.pp_backend.Responses.LoginResponse;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
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

    Firestore db;

    /**
     * The UserService constructor.
     * This constructor is used to initialize the Firestore database connection.
     * It reads the Firebase configuration from a file named 'config.json' located in the root directory of the project.
     * If the file is not found, it throws a FileNotFoundException.
     * If any other error occurs during the initialization, it throws the corresponding exception.
     *
     * @throws IOException If an error occurs during the process of reading the file or initializing the Firestore database.
     */
    public UserService() throws IOException {
        try {
            // Get the class loader of the application
            ClassLoader classLoader = PpBackendApplication.class.getClassLoader();

            // Get the URL of the 'config.json' file
            URL resource = classLoader.getResource("./config.json");

            // If the resource URL is null, throw a FileNotFoundException
            if (resource == null) {
                throw new FileNotFoundException("File not found");
            }

            // Create a File object from the resource URL
            File file = new File(Objects.requireNonNull(classLoader.getResource("./config.json")).getFile());

            // Create a FileInputStream from the file
            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

            // Build the FirebaseOptions object using the service account and the database URL
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)).setDatabaseUrl("https://default.firebaseio.com")
                    .build();

            // Initialize the FirebaseApp with the options
            FirebaseApp.initializeApp(options);

            // Get the Firestore database instance
            db = FirestoreClient.getFirestore();

            // Print a success message to the console
            System.out.println("Database connected");

        } catch (Exception E) {
            // Print the exception message to the console and rethrow the exception
            System.out.println(E.getMessage() + "HELLo");
            throw E;
        }
    }

    /**
     * This method is used to authenticate a user.
     * It takes an email and password as input, and checks them against the Firestore database.
     * If the email and password match a user in the database, it returns a LoginResponse with a status of true and a code of 200.
     * If the email and password do not match any user in the database, it returns a LoginResponse with a status of false and a code of 401.
     * If an exception occurs during the process, it returns a LoginResponse with a status of false and a code of 500.
     *
     * @param email    The email of the user trying to log in.
     * @param password The password of the user trying to log in.
     * @return LoginResponse object containing the status of the login attempt and a code representing the outcome.
     * @throws Exception If an error occurs during the process.
     */
    public LoginResponse login(String email, String password) {
        try {
            // verify email and password in firestore database
            QuerySnapshot snapshot = db.collection("users").
                    whereEqualTo("email", email).
                    whereEqualTo("password", password)
                    .get()
                    .get();
            if (snapshot.isEmpty()) {
                return new LoginResponse(false, 401);
            }
            return new LoginResponse(true, 200);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new LoginResponse(false, 500);
        }
    }

    public boolean addUser(User u) {
        try {

            db.collection("users").document(u.getEmail()).set(u).get();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


 /**
 * This method is used to update a user's extra information in the Firestore database.
 * It takes an email and a User object as input.
 * The User object contains the new values for the user's height, weight, gender, and date of birth.
 * The method first queries the Firestore database for a user with the provided email.
 * If the email does not match any user in the database, it returns false.
 * If a user is found, it updates the user's height, weight, gender, and date of birth with the new values from the User object.
 * After updating the user's information, it prints a success message to the console and returns true.
 * If an exception occurs during the process, it prints the exception message to the console and returns false.
 *
 * @param x The User object containing the new values for the user's height, weight, gender, and date of birth.
 * @return true if the user's information was successfully updated, false otherwise.
 * @throws Exception If an error occurs during the process.
 */
public boolean updateUserExtraStuff(User x) {
    try {
        // Query the Firestore database for a user with the provided email
        QuerySnapshot snapshot = db.collection("users").
                whereEqualTo("email", x.getEmail())
                .get()
                .get();

        // If the query result is empty, return false
        if (snapshot.isEmpty()) {
            return false;
        }
        // If a user is found, update the user's height, weight, gender, and date of birth with the new values from the User object
        User u =  snapshot.toObjects(User.class).getFirst();
        u.setHeight(x.getHeight());
        u.setWeight(x.getWeight());
        u.setGender(x.getGender());
        u.setDateofbirth(x.getDateofbirth());

        // Update the user's information in the Firestore database
        db.collection("users").document(u.getEmail()).set(u);

        // Print a success message to the console
        System.out.println("User Data Updated");

        // Return true to indicate that the user's information was successfully updated
        return true;

    } catch (Exception e) {
        // Print the exception message to the console and return false
        System.out.println(e.getMessage());
        return false;
    }
}
public User getUserByMail(String mail){
    try {
        // Query the Firestore database for a user with the provided email
        QuerySnapshot snapshot = db.collection("users").
                whereEqualTo("email", mail)
                .get()
                .get();

        // If the query result is empty, return null
        if (snapshot.isEmpty()) {
            return null;
        }
        System.out.println(snapshot.toObjects(User.class).get(0).getDateofbirth());

        // If a user is found, return the user object
        return snapshot.toObjects(User.class).get(0);
    } catch (Exception e) {
        // Print the exception message to the console and return null
        System.out.println(e.getMessage());
        return null;
    }
}

}
