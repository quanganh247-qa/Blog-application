package com.hangeulLearning.main.service;

import com.hangeulLearning.main.model.Comment;
import com.hangeulLearning.main.model.Post;

import java.util.List;

public interface PostService {

    Post createPost(Post postDto);

    List<Post> getAllPost();

    Post viewPostById(Long id);

    Post getPostById(Long id);

    void likeCount(Long id);

    List<Comment> getByNameContaining(String name);
}
