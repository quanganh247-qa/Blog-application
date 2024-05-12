package com.hangeulLearning.main.service.image;

import com.hangeulLearning.main.model.Post;
import com.hangeulLearning.main.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl {
    private final S3Service s3Service;
    private final PostRepository postRepository;
    public void uploadVideo(MultipartFile file){
        String urlObject = s3Service.uploadImage(file);
        s3Service.uploadImage(file);
        var post = new Post();
        post.setImg(urlObject);
        postRepository.save(post);

    }
}
