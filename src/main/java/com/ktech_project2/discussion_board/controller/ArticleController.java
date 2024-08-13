package com.ktech_project2.discussion_board.controller;

import com.ktech_project2.discussion_board.model.Article;
import com.ktech_project2.discussion_board.model.Board;
import com.ktech_project2.discussion_board.model.Comment;
import com.ktech_project2.discussion_board.service.ArticleService;
import com.ktech_project2.discussion_board.service.BoardService;
import com.ktech_project2.discussion_board.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("articles")
public class ArticleController {
    private final ArticleService articleService;
    private final BoardService boardService;
    private final CommentService commentService;

    public ArticleController(ArticleService articleService, BoardService boardService, CommentService commentService) {
        this.articleService = articleService;
        this.boardService = boardService;
        this.commentService = commentService;
    }
    //CREATE
    // /articles/new
    @GetMapping("new")
    public String createForm(Model model) {
        List<Board> boards = boardService.readAll();
        model.addAttribute("boards", boards);
        return "new";
    }

    @PostMapping("new")
    public String create(
            @RequestParam("boardId") Long boardId,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("password") String password) {

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
    // /articles/{articleId}
    @GetMapping("{articleId}")
    public String readOne(
            @PathVariable("articleId")
            Long articleId,
            Model model
    ) {Article article = articleService.readOne(articleId);
        List<Comment> comments = commentService.readAll(articleId);
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        return "read";
    }
    //READ ALL ARTICLES BY BOARD ID
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
        List<Board> boards = boardService.readAll();
        model.addAttribute("article", articleService.readOne(articleId));
        model.addAttribute("boards", boards);
        return "update";
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

    //DELETE /articles/{articleId}/delete/
    @PostMapping("/{articleId}/delete")
    public String delete(
            @PathVariable("articleId") Long articleId,
            @RequestParam("password") String password
    ) {
        articleService.delete(articleId, password);
        return "redirect:/articles";
    }

    // GET PREVIOUS ARTICLE -  NEXT ARTICLE
    @GetMapping("/articles/{articleId}")
    public String getArticleDetails(@PathVariable Long articleId, Model model) {
        Article article = articleService.readOne(articleId);
        if (article != null) {
            Board board = article.getBoard();
            Optional<Article> previousArticle = articleService.findPreviousArticle(board, articleId);
            Optional<Article> nextArticle = articleService.findNextArticle(board, articleId);

            model.addAttribute("article", article);
            model.addAttribute("previousArticle", previousArticle.orElse(null));
            model.addAttribute("nextArticle", nextArticle.orElse(null));

            System.out.println("Previous Article: " + previousArticle);
            System.out.println("Next Article: " + nextArticle);
            return "read";
        }
        return "redirect:/articles";
    }



}
