package com.hangeulLearning.main.controller;

import com.hangeulLearning.main.model.Comment;
import com.hangeulLearning.main.service.CommentService;
import com.hangeulLearning.main.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("/newComment")
    public ResponseEntity<?> createComment(@RequestParam Long id,@RequestParam String postedBy,@RequestBody() String content){
        try {
            commentService.createComment(id, content, postedBy);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable("id") Long id){
        return ResponseEntity.ok(commentService.getComments(id));
    }

    @GetMapping("/search/{name}")
    public  ResponseEntity<List<Comment>> getByNameContaining(@PathVariable("name") String name){
        try{
            return ResponseEntity.ok(postService.getByNameContaining(name));
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
