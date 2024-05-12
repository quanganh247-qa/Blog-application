package com.hangeulLearning.main.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private String content;
    private String postedBy;
    private String img;
    private Date date;
    private int likeCount;
    private int viewCount;
    @ElementCollection
    private List<String> tags;

}
