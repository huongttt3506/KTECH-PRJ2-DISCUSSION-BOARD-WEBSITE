package com.ktech_project2.discussion_board.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
public class Board {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @Getter
    @OneToMany (mappedBy = "board", cascade = CascadeType.ALL)
    private List<Article> articles;

    public Board(String name) {
        this.name = name;
    }
}
