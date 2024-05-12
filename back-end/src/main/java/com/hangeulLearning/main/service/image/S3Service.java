package com.hangeulLearning.main.service.image;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import static com.amazonaws.services.s3.model.CannedAccessControlList.PublicRead;

@Service
public class S3Service implements ImageService{
    public static final String BUCKET_NAME = "youtube-clone-spring";

    @Autowired
    private AmazonS3Client amazonS3Client;

//    https://docs.awspring.io/spring-cloud-aws/docs/3.1.0/reference/html/index.html#spring-cloud-aws-s3
    @Override
    public String uploadImage(MultipartFile file){
        var findName = StringUtils.getFilenameExtension(file.getOriginalFilename());
        var key = UUID.randomUUID().toString() + "." + findName;

        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try{
            amazonS3Client.putObject(BUCKET_NAME, key, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        amazonS3Client.setObjectAcl(BUCKET_NAME, key, PublicRead);
        return amazonS3Client.getResourceUrl(BUCKET_NAME, key);
    }

}
