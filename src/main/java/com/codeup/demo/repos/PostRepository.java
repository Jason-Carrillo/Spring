package com.codeup.demo.repos;

import com.codeup.demo.models.Post;
import com.codeup.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);
    Post findByBody(String desc); // mysql> select * from ads where description = ?;
    List<Post> findAllByTitleLikeOrBodyLike(String title,String desc);
    List<Post> findAllByTitleOrBodyIsLike(String title,String desc);
    List<Post> findAllByTitleIsLike(String title);
    List<Post> findAllByOwner(User owner);


}