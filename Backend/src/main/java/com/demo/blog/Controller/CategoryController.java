package com.demo.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.blog.Paylods.ApiResponse;
import com.demo.blog.Paylods.CategoryDto;
import com.demo.blog.Service.CategoryService;

@RestController
@RequestMapping("/apis/Category")
public class CategoryController {
	
	
	@Autowired
	private CategoryService catService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createcategory(@RequestBody CategoryDto cdto)
	{
		
		CategoryDto createdCat=this.catService.createCategory(cdto);
		
		return new ResponseEntity<CategoryDto>(createdCat,HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto cdto,@PathVariable Integer catId)
	{
		CategoryDto updatedCat=this.catService.updateCategory(cdto, catId);
		
		return new ResponseEntity<CategoryDto>(updatedCat,HttpStatus.OK);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId)
	{
		this.catService.deleteCategory(catId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully",true),HttpStatus.OK);
		
	}
	
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId)
	{
		CategoryDto cdto=this.catService.getCategory(catId);
		
		
		return new ResponseEntity<CategoryDto>(cdto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		List<CategoryDto> clist=this.catService.getCategories();
		
		return ResponseEntity.ok(clist);
	}
}
