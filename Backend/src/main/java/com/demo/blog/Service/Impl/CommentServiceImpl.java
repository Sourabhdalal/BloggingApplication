package com.demo.blog.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.blog.Exception.ResourceNotFoundException;
import com.demo.blog.Paylods.CommentDto;
import com.demo.blog.Repository.CommentRepo;
import com.demo.blog.Repository.PostRepo;
import com.demo.blog.Service.CommentService;
import com.demo.blog.entities.Comment;
import com.demo.blog.entities.Post;

@Service
public class CommentServiceImpl implements CommentService {
	
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId)
	{
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));

		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment=this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId)
	{
		Comment comm=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentId));
		this.commentRepo.delete(comm);
	}

}
