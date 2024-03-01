package com.demo.blog.Paylods;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	
	
	private int id;
	
	@NotEmpty
	@Size(min=3,message="Username must be atleast of 3 character")
	private String name;
	
	@Email(message="Email id is not valid !")
	private String email;
	
    @NotEmpty
	private String password;
	
	@NotEmpty
	private String about;
}
