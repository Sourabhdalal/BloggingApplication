package com.demo.blog.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.demo.blog.Exception.ResourceNotFoundException;
import com.demo.blog.Paylods.UserDto;
import com.demo.blog.Repository.UserRepo;
import com.demo.blog.Service.UserService;
import com.demo.blog.entities.User;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto user) {
		
		
		User u=this.dtoToUser(user);
		
		
		userRepo.save(u);
		
		return this.userToUserDto(u);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		
		User u=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setAbout(user.getAbout());
		
		User updatedUser=this.userRepo.save(u);
		return this.userToUserDto(updatedUser);
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User u=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

		return this.userToUserDto(u);
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User u=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

		this.userRepo.delete(u);
		
		
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> ulist=this.userRepo.findAll();
		
		List<UserDto> uDto=ulist.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
		return uDto;
	}
	
	
	private User dtoToUser(UserDto userDto)
	{
		//User u=this.modelMapper.map(userDto, User.class);
		
		User u=new User();
		u.setId(userDto.getId());
		u.setName(userDto.getName());
		u.setEmail(userDto.getEmail());
		u.setAbout(userDto.getAbout());
		u.setPassword(userDto.getPassword());
		
		return u;
	}
	 
	private UserDto userToUserDto(User user)
	{
		UserDto u=this.modelMapper.map(user, UserDto.class);
//		u.setId(user.getId());
//		u.setName(user.getName());
//		u.setEmail(user.getEmail());
//		u.setAbout(user.getAbout());
//		u.setPassword(user.getPassword());
		
		return u;
	}
	
	@Override
	public UserDto logIn(String username, String password) {
		
		Optional<User> u=this.userRepo.findByUsernameAndPassword(username, password);
		
		
		if(u.isPresent())
		{
		   return this.modelMapper.map(u.get(),UserDto.class);
		}
		return null;
	}

 }
