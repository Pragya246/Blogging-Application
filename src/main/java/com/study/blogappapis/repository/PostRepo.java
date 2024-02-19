package com.study.blogappapis.repository;

import com.study.blogappapis.models.Category;
import com.study.blogappapis.models.Post;
import com.study.blogappapis.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    Page<Post> findByUser (User user, Pageable p);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
    @Query("select p from Post p where p.title like :keyword")
    List<Post> searchByTitle(@Param("keyword") String keyword);
}
