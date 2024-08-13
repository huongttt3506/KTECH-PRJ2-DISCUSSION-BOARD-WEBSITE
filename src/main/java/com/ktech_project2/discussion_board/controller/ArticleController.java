package com.ktech_project2.discussion_board.controller;

import com.ktech_project2.discussion_board.model.Article;
import com.ktech_project2.discussion_board.model.Board;
import com.ktech_project2.discussion_board.service.ArticleService;
import com.ktech_project2.discussion_board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("articles")
public class ArticleController {
    private final ArticleService articleService;
    private final BoardService boardService;

    public ArticleController(ArticleService articleService, BoardService boardService) {
        this.articleService = articleService;
        this.boardService = boardService;
    }
    //CREATE
    // /articles/new
    @GetMapping("new")
    public String createForm(Model model) {
        List<Board> boards = boardService.readAll();
        model.addAttribute("boards", boards);
        return "/articles/new";
    }

    @PostMapping("new")
    public String create(
            @RequestParam("boardId")
            Long boardId,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password

    ) {
        Long articleId = articleService.create(boardId, title, content, password).getId();
        return String.format("redirect:/articles/%d", articleId);
    }


    //READ
    //READ ALL
    @GetMapping // /articles
    public String readAll(Model model) {
        model.addAttribute("articles", articleService.readAll());
        return "boards.html";
    }

    //READ ONE
    // /articles}/{articleId}
    @GetMapping("{articleId}")
    public String readOne(
            @PathVariable("articleId")
            Long articleId,
            Model model
    ) {
        model.addAttribute("article", articleService.readOne(articleId));
        return "articles/read";
    }
    //READ ALL ARTICLES BY BOARDID
    // Endpoint to get all articles by Board ID
    @GetMapping("/{boardId}/articles")
    public String getArticlesByBoardId(
            @PathVariable("boardId")
            Long boardId,
            Model model
    ) {
        List<Article> articles = articleService.findArticlesByBoardId(boardId);
        model.addAttribute("articles", articles);
        return "articles"; // Return the view name to display the articles
    }

    //UPDATE
    @GetMapping("{articleId}/update")
    public String updateView(
            @PathVariable("articleId")
            Long articleId,
            Model model
    ) {
        model.addAttribute("article", articleService.readOne(articleId));
        return "articles/update";
    }
    @PostMapping("{articleId}/update")
    public String update(
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("boardId")
            Long boardId,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password
    ) {
        articleService.update(articleId, boardId, title, content, password);
        return String.format("redirect:/articles/%d", articleId);
    }



    //UPDATE
    // /boards/{boardId}/article/

    //DELETE /articles/{articleId}/delete/


    // COMMENT
    // /articles/{articleId}/comments/

    // DELETE COMMENT
    // /articles/{articleId}/comments/{commentId}/delete/

    // GET PREVIOUS ARTICLE

    // GET NEXT ARTICLE


}
