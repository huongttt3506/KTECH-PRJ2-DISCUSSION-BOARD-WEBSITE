package com.ktech_project2.discussion_board.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String content;
    private String password;
    private LocalDateTime createAt;

    @ManyToOne
    private Article article;

}
