package com.ktech_project2.discussion_board.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String password;
    private LocalDateTime createAt;

    @ManyToOne
    private Article article;

    public Comment(String content, String password, LocalDateTime createAt, Article article) {
        this.content = content;
        this.password = password;
        this.createAt = createAt;
        this.article = article;
    }
}
