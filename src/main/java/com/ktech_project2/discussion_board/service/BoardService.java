package com.ktech_project2.discussion_board.service;

import com.ktech_project2.discussion_board.model.Board;
import com.ktech_project2.discussion_board.repository.BoardRepository;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    //Create
    public Board create(String name) {
        if (boardRepository.findByName(name).isEmpty()) {
            Board board = new Board(name);
            return boardRepository.save(board);
        }
        throw new IllegalArgumentException("Board with this name already exists");
    }


    // Read all boards
    public List<Board> readAll() {
        return boardRepository.findAll();
    }

    //Read one (Read Board By Id)
    public Board readOne(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

}
