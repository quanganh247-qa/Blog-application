package com.hangeulLearning.main.service;

import com.hangeulLearning.main.model.Comment;
import com.hangeulLearning.main.model.Post;
import com.hangeulLearning.main.repository.CommentRepository;
import com.hangeulLearning.main.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Override
    public Comment createComment(Long id, String content, String postedBy){
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()){
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setPostedBy(postedBy);
            comment.setPost(optionalPost.get());
            comment.setCreatedDate(new Date());
            return commentRepository.save(comment);
        }else {
            throw new RuntimeException("Post not found");
        }
    }


    @Override
    @Transactional
    public List<Comment> getComments(Long id){
        return commentRepository.findByPostId(id);
    }


}
