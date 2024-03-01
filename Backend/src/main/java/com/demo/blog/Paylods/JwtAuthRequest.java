package com.demo.blog.Paylods;

import lombok.Data;

@Data
public class JwtAuthRequest 
{
   private String username;
   
   private String password;
}
