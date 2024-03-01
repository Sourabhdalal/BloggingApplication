package com.demo.blog.Paylods;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto
{
	private Integer categoryId;
	
	@NotBlank
	@Size(min=4)
	private String categoryName;
	
	@NotBlank
	@Size(min=10)
	private String categoryDescription;
}
