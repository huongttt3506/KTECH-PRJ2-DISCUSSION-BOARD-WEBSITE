package com.ktech_project2.discussion_board.service;

import com.ktech_project2.discussion_board.repository.ArticleRepository;
import com.ktech_project2.discussion_board.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    // CREATE COMMENT


    // DELETE COMMENT

}
