package com.example.pp_backend.Controllers;

import com.example.pp_backend.Models.User;
import com.example.pp_backend.Responses.LoginResponse;
import com.example.pp_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService ;

    @PostMapping("/login")
    public LoginResponse Login(@RequestParam(name = "email") String email , @RequestParam(name = "password") String password){
       return  userService.login(email,password);
    }
    @PostMapping("/addUser")
    public boolean AddUser(@RequestBody User u){
        return  userService.addUser(u);
    }
}
