package com.ktech_project2.discussion_board.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
public class Hashtag {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @ManyToMany(mappedBy = "hashtags")
    private List<Article> articles;



}
