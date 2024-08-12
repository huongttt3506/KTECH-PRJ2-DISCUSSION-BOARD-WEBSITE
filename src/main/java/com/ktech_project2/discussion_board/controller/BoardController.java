package com.ktech_project2.discussion_board.controller;

import com.ktech_project2.discussion_board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
// /boards
@RequestMapping("/boards")
public class BoardController {
    private final BoardService service;


    public BoardController(BoardService service) {
        this.service = service;
    }
    // don't have create, update, delete Method. get boards data from boards.sql

    // READ ALL
    // /boards/
    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("boards", service.readAll());
        return "boards.html";
    }

    //READ ONE
    // /boards/{boardId}
    @GetMapping("/boards/{boardId}")
    public String readOne(
            @PathVariable("boardId")
            Long id,
            Model
            model
    ) {
        model.addAttribute("board", service.readOne(id));
        return "board.html";
    }


}
