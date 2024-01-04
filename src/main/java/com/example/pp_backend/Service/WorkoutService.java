package com.example.pp_backend.Service;

import com.example.pp_backend.Models.Workout;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class WorkoutService {

    Firestore db ;

    public WorkoutService(){
        db = FirestoreClient.getFirestore();

    }
    public List<Workout> getWorkouts() throws ExecutionException, InterruptedException {
        return db.collection("workout").get().get().toObjects(Workout.class);
    }
}
