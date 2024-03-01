package com.demo.blog.Service.Impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.blog.Exception.ResourceNotFoundException;
import com.demo.blog.Paylods.PostDto;
import com.demo.blog.Paylods.PostResponse;
import com.demo.blog.Repository.CategoryRepo;
import com.demo.blog.Repository.PostRepo;
import com.demo.blog.Repository.UserRepo;
import com.demo.blog.Service.PostService;
import com.demo.blog.entities.Category;
import com.demo.blog.entities.Post;
import com.demo.blog.entities.User;


@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User u=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));

		Post post=this.modelMapper.map(postDto, Post.class);
		
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(cat);
		post.setUser(u);
		
		
		Post newPost=this.postRepo.save(post);
		
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(post.getImageName());
		
		Post updatedPost=this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
		
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		Sort s=(sortDir.equalsIgnoreCase("asce"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable p= PageRequest.of(pageNumber, pageSize,s);
		
		Page<Post> pagePost=this.postRepo.findAll(p);
		
		List<Post> postList=pagePost.getContent();
		
		List<PostDto> plist=postList.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postRes=new PostResponse();
		
		
		postRes.setContent(plist);
		postRes.setPageNumber(pagePost.getNumber());
		postRes.setPageSize(pagePost.getSize());
		postRes.setTotalElements(pagePost.getTotalElements());
		postRes.setTotalpages(pagePost.getTotalPages());
		postRes.setLastPage(pagePost.isLast());
		
		return postRes;
	}

	@Override
	public PostDto getPostByid(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));

		List<Post> postList=this.postRepo.findByCategory(cat);
		
		List<PostDto> plist=postList.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		return plist;
	}	

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));

		List<Post> postList=this.postRepo.findByUser(user);
		
		List<PostDto> plist=postList.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		return plist;
		
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> plist=this.postRepo.searchByTitle("%"+keyword+"%");
		
		List<PostDto> pDto=plist.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return pDto;
	}

}
