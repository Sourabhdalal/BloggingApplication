package com.demo.blog.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.blog.Paylods.UserDto;
import com.demo.blog.Service.UserService;



@RequestMapping("/login")
@RestController
public class LoginController
{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/{userName}/{password}")
	public ResponseEntity<UserDto> logIn(@PathVariable String userName,@PathVariable String password)
	{
	   System.out.println(userName+" "+password);
	   UserDto u=this.userService.logIn(userName, password);
	   System.out.println(u);
	   return new ResponseEntity<UserDto>(u,HttpStatus.OK);
	}
}