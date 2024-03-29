package com.example.pp_backend.Controllers;


import com.example.pp_backend.Models.Plan;
import com.example.pp_backend.Models.Workout;
import com.example.pp_backend.Service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getplans")
    public List<Plan> getPlans() throws ExecutionException, InterruptedException {
        return  workoutService.getPlans();
    }
    @PostMapping("/addgoal")
    public boolean addGoal(@RequestParam(name="goal")String goal , @RequestParam(name="email") String email ,@RequestParam(name="username") String username) throws ExecutionException, InterruptedException {
        return workoutService.addGoal(goal,email,username);
    }
    @GetMapping("/getgoal")
    public String getGoal(@RequestParam(name="email") String email) throws ExecutionException, InterruptedException {
        return workoutService.getGoal(email);
    }
}
