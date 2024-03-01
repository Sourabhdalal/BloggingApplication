package com.demo.blog.Service;

import java.util.List;

import com.demo.blog.Paylods.CategoryDto;

public interface CategoryService
{
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	void deleteCategory(Integer categoryId);
	
	CategoryDto getCategory(Integer categoryId);
	
	List<CategoryDto> getCategories();
}
