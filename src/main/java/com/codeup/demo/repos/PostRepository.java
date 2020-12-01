package com.codeup.demo.repos;


import com.codeup.demo.Post;
import com.codeup.demo.repos.PostRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByIdEquals(Long id);
}