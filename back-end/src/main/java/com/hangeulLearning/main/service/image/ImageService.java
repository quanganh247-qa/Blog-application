package com.hangeulLearning.main.service.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    //    https://docs.awspring.io/spring-cloud-aws/docs/3.1.0/reference/html/index.html#spring-cloud-aws-s3
    String uploadImage(MultipartFile file);
}
