package com.ktech_project2.discussion_board.controller;

import com.ktech_project2.discussion_board.model.Comment;
import com.ktech_project2.discussion_board.service.ArticleService;
import com.ktech_project2.discussion_board.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articles/{articleId}/comments")
public class CommentController {
    private final CommentService commentService;
    private final ArticleService articleService;

    public CommentController(CommentService commentService, ArticleService articleService) {
        this.commentService = commentService;
        this.articleService = articleService;
    }
    @GetMapping
    public String readComments(@PathVariable("articleId") Long articleId, Model model) {
        List<Comment> comments = commentService.readAll(articleId);
        model.addAttribute("comments", comments);
        return "comments";
    }

    // CREATE COMMENT
    @PostMapping
    public String addComment(
            @PathVariable("articleId") Long articleId,
            @RequestParam("content") String content,
            @RequestParam("password") String password) {
        commentService.create(articleId, content, password);
        return String.format("redirect:/articles/%d", articleId);
    }

    //DELETE COMMENT
    @PostMapping("/{commentId}/delete")
    public String deleteComment(
            @PathVariable("articleId") Long articleId,
            @PathVariable("commentId") Long commentId,
            @RequestParam("password") String password) {

        commentService.delete(articleId, commentId, password);
        return String.format("redirect:/articles/%d", articleId);
    }
}
