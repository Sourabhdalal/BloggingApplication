package com.demo.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>
{

}
