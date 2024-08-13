package com.ktech_project2.discussion_board.controller;

import com.ktech_project2.discussion_board.service.ArticleService;
import com.ktech_project2.discussion_board.service.CommentService;
import org.springframework.stereotype.Controller;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final ArticleService articleService;

    public CommentController(CommentService commentService, ArticleService articleService) {
        this.commentService = commentService;
        this.articleService = articleService;
    }

    // CREATE COMMENT

    //DELETE COMMENT


}
