package com.ktech_project2.discussion_board.service;

import com.ktech_project2.discussion_board.model.Article;
import com.ktech_project2.discussion_board.model.Board;
import com.ktech_project2.discussion_board.model.Comment;
import com.ktech_project2.discussion_board.repository.ArticleRepository;
import com.ktech_project2.discussion_board.repository.BoardRepository;
import com.ktech_project2.discussion_board.repository.CommentRepository;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public ArticleService(
            ArticleRepository articleRepository,
            BoardRepository boardRepository,
            CommentRepository commentRepository
    ) {
        this.articleRepository = articleRepository;
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    //Create
    public Article create(
            Long boardId,
            String title,
            String content,
            String password) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("Board not found with id: " + boardId));

        Article article = new Article(title, content, password);
        article.setBoard(board);
        return articleRepository.save(article);
    }

    // READ ALL
    public List<Article> readAll() {
        return articleRepository.findAll();
    }

    // READ ONE
    public Article readOne(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Article update(
            Long id,
            Long boardId,
            String title,
            String content,
            String password
    ) {
        //Find article by id
        Article target = articleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Article not found with id: " + id));


        // Check password
        if (!target.getPassword().equals(password)) {
            throw new IllegalArgumentException("Password incorrect");
        }
        // Update properties of Article
        target.setTitle(title);
        target.setContent(content);

        // boardId not null, update board
        if (boardId != null) {
            Board board = boardRepository.findById(boardId)
                    .orElseThrow(() -> new NoSuchElementException("Board not found with id: " + boardId));
            target.setBoard(board);
        }

        return articleRepository.save(target);
    }

    //DELETE
    public void delete(Long id, String password) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Article not found with id: " + id));

        if (!article.getPassword().equals(password)) {
            throw new IllegalArgumentException("Password incorrect");
        }

        articleRepository.deleteById(id);
    }

    // Method to find all articles by Board ID
    public List<Article> findArticlesByBoardId(Long boardId) {
        boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("Board not found with id: " + boardId));
        return articleRepository.findByBoardId(boardId);
    }


    // Find previous article in the same board
    public Optional<Article> findPreviousArticle(Board board, Long articleId) {
        return articleRepository.findPreviousArticle(board, articleId);
    }
    // Find next article in the same board
    public Optional<Article> findNextArticle(Board board, Long articleId) {
        return articleRepository.findNextArticle(board, articleId);
    }







}
