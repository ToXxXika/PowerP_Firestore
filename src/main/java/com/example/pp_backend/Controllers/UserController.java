package com.example.pp_backend.Controllers;

import com.example.pp_backend.Models.User;
import com.example.pp_backend.Responses.LoginResponse;
import com.example.pp_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/login")
    public LoginResponse Login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        System.out.println(email);
        System.out.println(password);
        return userService.login(email, password);
    }

    @PostMapping("/addUser")
    public boolean AddUser(@RequestBody User u) {
        System.out.println(u.getFirstname());
        return userService.addUser(u);
    }
    @PostMapping("/updateUser")
    public boolean UpdateUser(@RequestBody User u) {
        System.out.println(u.getFirstname());
        return userService.updateUserExtraStuff(u);
    }
    @GetMapping("/getUser")
    public User getUser(@RequestParam(name = "email") String email) {
        return userService.getUserByMail(email);
    }
}
