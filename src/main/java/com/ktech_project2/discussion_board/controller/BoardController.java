package com.ktech_project2.discussion_board.controller;

import com.ktech_project2.discussion_board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("boards")
public class BoardController {
    private final BoardService service;


    public BoardController(BoardService service) {
        this.service = service;
    }

    //CREATE
    @GetMapping("create")
    public String createView() {
        return "boards/create.html";
    }

    @PostMapping("create")
    public String create(
            @RequestParam("name") String name
    ) {
        Long id = service.create(name).getId();
        //POST - redirect - GET
        return String.format("redirext:/boards/%d");
    }
    // READ ALL
    // /boards/
    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("boards", service.readAll());
        return "boards/read.html";
    }

    //READ ONE
    // /boards/{boardId}
    @GetMapping("{id}")
    public String readOne(
            @PathVariable("id")
            Long id,
            Model
            model
    ) {
        model.addAttribute("boards", service.readOne(id));
        return "boards/read.html";
    }

    //UPDATE

    //DELETE

}
