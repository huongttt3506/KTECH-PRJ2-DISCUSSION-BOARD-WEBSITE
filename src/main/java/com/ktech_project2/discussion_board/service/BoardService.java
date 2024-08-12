package com.ktech_project2.discussion_board.service;

import com.ktech_project2.discussion_board.model.Board;
import com.ktech_project2.discussion_board.repository.BoardRepository;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    //Create
    public Board create(String name) {
        Board board = new Board(name);
        return boardRepository.save(board);
    }


    // Read all boards
    public List<Board> readAll() {
        return boardRepository.findAll();
    }

    //Read one (Read Board By Id)
    public Board readOne(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    // Update Board
    public Board update(Long id, String name) {
        Optional<Board> optionalTarget = boardRepository.findById(id);
        if (optionalTarget.isEmpty()) return null;
        Board target = optionalTarget.get();
        target.setName(name);
        return boardRepository.save(target);
    }

    // deleteBoard
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }




}
