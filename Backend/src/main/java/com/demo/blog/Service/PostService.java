package com.demo.blog.Service;

import java.util.List;

import com.demo.blog.Paylods.PostDto;
import com.demo.blog.Paylods.PostResponse;

public interface PostService 
{
	PostDto createPost(PostDto postDto,Integer userid,Integer categoryId);
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	PostDto getPostByid(Integer postId);
	
	List<PostDto> getPostByCategory(Integer CategoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> searchPost(String keyword);
}
