package com.hangeulLearning.main.controller;
import com.hangeulLearning.main.model.Post;
import com.hangeulLearning.main.repository.PostRepository;
import com.hangeulLearning.main.service.PostService;
import com.hangeulLearning.main.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final ImageService imageService;
    private final PostRepository postRepository;

//    @PostMapping("/uploadImage")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
//        imageService.uploadImage(file);
//    }
    @PostMapping("/newPost")
    public ResponseEntity<Post> createPost(@ModelAttribute Post post,@RequestParam("file") MultipartFile file ){
        Post postDto = postService.createPost(post);
        String url = imageService.uploadImage(file);
        postDto.setImg(url);
        postRepository.save(postDto);
        if (postDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/allPosts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> postDto = postService.getAllPost();
        if (postDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") Long id ){
        try {
            Post post = postService.getPostById(id);
            return ResponseEntity.ok(post);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( e.getMessage());
        }
    }

    @GetMapping("/viewPost/{id}")
    public ResponseEntity<?> viewPostById(@PathVariable("id") Long id ){
        try {
            Post post = postService.viewPostById(id);
            return ResponseEntity.ok(post);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( e.getMessage());
        }
    }

}
