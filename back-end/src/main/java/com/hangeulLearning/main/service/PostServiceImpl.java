package com.hangeulLearning.main.service;

import com.hangeulLearning.main.model.Comment;
import com.hangeulLearning.main.model.Post;
import com.hangeulLearning.main.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;


    @Override
    public Post createPost(Post post){
        post.setDate(new Date());
        post.setViewCount(0);
        post.setLikeCount(0);
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPost(){
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public Post viewPostById(Long id){
        Optional<Post> opPost = postRepository.findById(id);
        if(opPost.isPresent()){
            Post post = opPost.get();
            return post;
        }
        else {
            throw new RuntimeException("Post not found");
        }
    }
    @Override
    public Post getPostById(Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            post.setViewCount(post.getViewCount() + 1);
            return postRepository.save(post);
        }
        else {
            throw new RuntimeException("Post not found");
        }
    }
    @Override
    public void likeCount(Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            post.setLikeCount(post.getLikeCount() + 1);
            postRepository.save(post);
        }
        else {
            throw new RuntimeException("Post not found");
        }
    }

    @Override
    public List<Comment> getByNameContaining(String name){
        return postRepository.findAllByNameContaining(name);
    }

}
