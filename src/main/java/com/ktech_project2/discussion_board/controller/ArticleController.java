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
    @GetMapping("/{articleId}")
    public String readOne(
            @PathVariable("articleId") Long articleId,
            Model model
    ) {
        Article article = articleService.readOne(articleId);
        List<Comment> comments = commentService.readAll(articleId);
        Board board = article.getBoard();


        List<Article> nextArticles = articleService.findNextArticles(articleId, board.getId());

        List<Article> previousArticles = articleService.findPreviousArticles(articleId, board.getId());


        Article nextArticle = nextArticles.isEmpty() ? null : nextArticles.get(0);
        Article previousArticle = previousArticles.isEmpty() ? null : previousArticles.get(0);

        model.addAttribute("article", article);
        model.addAttribute("comments", comments);
        model.addAttribute("nextArticle", nextArticle);
        model.addAttribute("previousArticle", previousArticle);

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

    // SEARCH KEY WORD
    @GetMapping("/search")
    public String search(
            @RequestParam("keyword") String keyword,
            @RequestParam("searchIn") String searchIn,
            Model model) {

        List<Article> articles;

        switch (searchIn) {
            case "title":
                articles = articleService.searchByTitle(keyword);
                break;
            case "content":
                articles = articleService.searchByContent(keyword);
                break;
            case "both":
                articles = articleService.searchByTitleOrContent(keyword);
                break;
            default:
                articles = List.of();
                break;
        }

        model.addAttribute("articles", articles);
        return "searchResults";
    }


}
