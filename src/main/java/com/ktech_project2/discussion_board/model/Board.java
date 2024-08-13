package com.ktech_project2.discussion_board.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Data
@Entity
public class Board {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany (mappedBy = "board", cascade = CascadeType.ALL)
    private List<Article> articles;


    public Board() {
    }

    public Board(String name) {
        this.name = name;
    }
}
