package com.example.pp_backend.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Plan {
   private String name;
   private String image ;
   private String kcal ;
   private String time ;
   private double progress;
}
