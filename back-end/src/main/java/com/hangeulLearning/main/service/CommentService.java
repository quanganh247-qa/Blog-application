package com.hangeulLearning.main.service;

import com.hangeulLearning.main.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment createComment(Long id, String content, String postedBy);

    List<Comment> getComments(Long id);

}
