package com.demo.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.blog.Paylods.ApiResponse;
import com.demo.blog.Paylods.UserDto;
import com.demo.blog.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apis/users")
@CrossOrigin(origins="*",allowedHeaders="*")
public class UserController 
{
	@Autowired
	UserService uService;
	
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		System.out.println(userDto.getPassword());
		UserDto createdUser = this.uService.createUser(userDto);
		
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId)
	{
		UserDto updatedUser = this.uService.updateUser(userDto, userId);
		
		return ResponseEntity.ok(updatedUser);
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<List<UserDto>> displayAllUser()
	{
		return ResponseEntity.ok(this.uService.getAllUsers());
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> singleUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(this.uService.getUserById(userId));
	}
	
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
	{
		this.uService.deleteUser(userId);
		//another method using by making class ApiResponse
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Delete Successfully",true),HttpStatus.OK);
		
		//return new ResponseEntity<>(Map.of("message","User delete Successfully"),HttpStatus.OK);
	}
}
