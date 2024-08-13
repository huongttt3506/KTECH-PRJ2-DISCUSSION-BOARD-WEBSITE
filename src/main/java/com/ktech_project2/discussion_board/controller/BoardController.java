package com.ktech_project2.discussion_board.controller;

import com.ktech_project2.discussion_board.model.Article;
import com.ktech_project2.discussion_board.model.Board;
import com.ktech_project2.discussion_board.service.ArticleService;
import com.ktech_project2.discussion_board.service.BoardService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
// /boards
@RequestMapping("boards")
public class BoardController {
    private final BoardService boardService;
    private final ArticleService articleService;

    public BoardController(BoardService boardService, ArticleService articleService) {
        this.boardService = boardService;
        this.articleService = articleService;
    }
    // READ ALL BOARDS
    // /boards
    @GetMapping
    public String readAllArticles(Model model) {
        List<Board> boards = boardService.readAll();
        List<Article> articles = articleService.readAll();
        model.addAttribute("boards", boards);
        model.addAttribute("articles", articles);
        model.addAttribute("selectedBoard", null);
        return "boards.html";
    }
    //read all articles by boardId
    @GetMapping("{boardId}")
    public String readArticlesByBoard(
            @PathVariable("boardId")
            Long boardId,
            Model model
    ) {
        List<Board> boards = boardService.readAll();
        Board selectedBoard = boardService.readOne(boardId);
        List<Article> articles = articleService.findArticlesByBoardId(boardId);

        model.addAttribute("boards", boards);
        model.addAttribute("articles", articles);
        model.addAttribute("selectedBoard", selectedBoard);
        return "boards.html";
    }
}




