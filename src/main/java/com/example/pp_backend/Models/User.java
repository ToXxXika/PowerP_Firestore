package com.example.pp_backend.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String firstname ;
    String lastname;
    String email ;
    String password ;
    String dateofbirth;
    String Gender ;
    int Weight ;
    int Height ;
    public User(String firstName,String lastName,String email,String password){
        this.firstname = firstName ;
        this.lastname = lastName ;
        this.email = email ;
        this.password = password ;
    }
    public User(String email,String gender,int Height,int weight,String dateofbirth){
        this.email = email;
        this.Gender=gender;
        this.Height=Height;
        this.Weight=weight;
        this.dateofbirth=dateofbirth;
    }

}
