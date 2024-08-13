package com.ktech_project2.discussion_board.service;

import com.ktech_project2.discussion_board.model.Article;
import com.ktech_project2.discussion_board.model.Board;
import com.ktech_project2.discussion_board.repository.ArticleRepository;
import com.ktech_project2.discussion_board.repository.BoardRepository;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    public ArticleService(
            ArticleRepository articleRepository,
            BoardRepository boardRepository
    ) {
        this.articleRepository = articleRepository;
        this.boardRepository = boardRepository;
    }

    //Create
    public Article create(
            Long boardId,
            String title,
            String content,
            String password
    ) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow();

        Article article = new Article(
                title,
                content,
                password
        );
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
        Optional<Article> optionalTarget = articleRepository.findById(id);
        if (optionalTarget.isEmpty()) return null;

        Article target = optionalTarget.get();

        // Check password
        if (!target.getPassword().equals(password)) {
            throw new IllegalArgumentException("Password incorrect");
        }
        // Update properties of Article
        target.setTitle(title);
        target.setContent(content);

        // boardId not null, update board
        if (boardId != null)
        {
            Board board = boardRepository.findById(boardId)
                    .orElseThrow();
            target.setBoard(board);
        }

        return articleRepository.save(target);
    }

    //DELETE
    public void delete(Long id, String password) {
        Optional<Article> optional = articleRepository.findById(id);

        if (optional.isEmpty()) {
            throw new NoSuchElementException("Article not found");
        }

        Article article = optional.get();
        if (!article.getPassword().equals(password)) {
            throw new IllegalArgumentException("Password incorrect");
        }

        articleRepository.deleteById(id);
    }
    // Method to find all articles by Board ID
    public List<Article> findArticlesByBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found with id: " + boardId));
        return articleRepository.findByBoardId(boardId);
    }


    //




}
