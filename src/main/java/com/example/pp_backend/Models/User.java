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
    int Weight ;
    int Height ;

}
