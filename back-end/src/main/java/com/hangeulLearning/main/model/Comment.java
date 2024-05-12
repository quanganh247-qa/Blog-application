package com.hangeulLearning.main.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String postedBy;
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;

}
