package com.ktech_project2.discussion_board.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Article {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Setter
    private String content;

    @Setter
    private String password;

    @Setter
    private LocalDateTime createAt;


    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
    }

    @ManyToOne
    private Board board;

    @OneToMany (mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;


//    @ManyToMany
//    @JoinTable(
//            name = "article_hashtag",
//            joinColumns = @JoinColumn(name = "articleId"),
//            inverseJoinColumns = @JoinColumn(name = "hashtagId")
//    )
//    private List<HashTag> hashtags;

    public Article(String title, String content, String password) {
        this.title = title;
        this.content = content;
        this.password = password;
    }

    public void setBoard(Board board) {
        this.board = board;
        if (board !=null) {
            board.getArticles().add(this);
        }
    }
}
