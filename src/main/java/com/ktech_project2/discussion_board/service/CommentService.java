package com.ktech_project2.discussion_board.service;

import com.ktech_project2.discussion_board.model.Article;
import com.ktech_project2.discussion_board.model.Comment;
import com.ktech_project2.discussion_board.repository.ArticleRepository;
import com.ktech_project2.discussion_board.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }
    // READ ALL COMMENTS BY ARTICLE ID
    public List<Comment> readAll(Long articleId) {
        return articleRepository.findById(articleId)
                .map(Article::getComments)
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    // CREATE COMMENT
    public Comment create(Long articleId, String content, String password) {
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            Comment comment = new Comment(content, password, LocalDateTime.now(), article);
            return commentRepository.save(comment);
        } else {
            throw new RuntimeException("Article not found");
        }
    }
    // DELETE COMMENT
    public void delete(Long commentId, Long id, String password) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            if (comment.getPassword().equals(password)) {
                commentRepository.delete(comment);
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("Comment not found");
        }
    }
}
