package com.example.pp_backend.Service;

import com.example.pp_backend.Models.Goal;
import com.example.pp_backend.Models.Plan;
import com.example.pp_backend.Models.Workout;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class WorkoutService {

    Firestore db;

    public WorkoutService() {
        db = FirestoreClient.getFirestore();

    }

    public List<Plan> getPlans() throws ExecutionException,InterruptedException{
        return db.collection("plan").get().get().toObjects(Plan.class);
    }

    public List<Workout> getWorkouts() throws ExecutionException, InterruptedException {
        return db.collection("workout").get().get().toObjects(Workout.class);
    }

    public boolean addGoal(String goal, String email, String username) {
    try {
        // Check if a document with the given email already exists
        if (db.collection("goals").document(email).get().get().exists()) {
            return false;
        }

        db.collection("goals").document(email).set(new Goal(goal, username));
        return true;
    } catch (Exception e) {
        System.out.println(e);
        return false;
    }
}

    public String getGoal(String email) {
        try {
            // Check if a document with the given email already exists
            if (db.collection("goals").document(email).get().get().exists()) {
                return db.collection("goals").document(email).get().get().toObject(Goal.class).getGoal();
            }

            return "No goal set";
        } catch (Exception e) {
            System.out.println(e);
            return "No goal set";
        }
    }
}
