package com.hangeulLearning.main.repository;

import com.hangeulLearning.main.model.Comment;
import com.hangeulLearning.main.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Comment> findAllByNameContaining(String name);

}
