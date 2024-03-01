package com.demo.blog.Service;

import java.util.List;


import com.demo.blog.Paylods.UserDto;

public interface UserService 
{
  UserDto createUser(UserDto user);
  
  UserDto updateUser(UserDto user,Integer userId);
  
  UserDto getUserById(Integer userId);
  
  void deleteUser(Integer userId);
  
  List<UserDto> getAllUsers();
  
  UserDto logIn(String username,String password);
}
