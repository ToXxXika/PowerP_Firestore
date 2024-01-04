package com.example.pp_backend.Controllers;


import com.example.pp_backend.Models.Workout;
import com.example.pp_backend.Service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/workout")
@CrossOrigin("*")
public class WorkoutController {


    @Autowired
    WorkoutService workoutService;
    @GetMapping("/t")
    public List<Workout> getWorkouts() throws ExecutionException, InterruptedException {
        System.out.println("here");
        return  workoutService.getWorkouts();
    }
}
