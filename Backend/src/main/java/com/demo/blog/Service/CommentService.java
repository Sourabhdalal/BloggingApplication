package com.demo.blog.Service;

import com.demo.blog.Paylods.CommentDto;

public interface CommentService 
{
   CommentDto createComment(CommentDto commentDto,Integer postId);
   
   void deleteComment(Integer commentId);
}
