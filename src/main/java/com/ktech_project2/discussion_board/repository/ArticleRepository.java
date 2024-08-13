package com.ktech_project2.discussion_board.repository;

import com.ktech_project2.discussion_board.model.Article;
import com.ktech_project2.discussion_board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository
extends JpaRepository<Article, Long> {

    //Find all article belong to a specific board
    List<Article> findByBoardId(Long boardId);

    // Query to find Previous Article
    @Query("SELECT a FROM Article a WHERE a.board = :board AND a.id < :articleId ORDER BY a.id DESC")
    Optional<Article> findPreviousArticle(
            @Param("board")
            Board board,
            @Param("articleId")
            Long articleId
            );

    // Query to find Next Article
    @Query("SELECT a FROM Article a WHERE a.board = :board AND a.id > :articleId ORDER BY a.id ASC")
    Optional<Article> findNextArticle(
            @Param("board")
            Board board,
            @Param("articleId")
            Long articleId
    );

//    // Query to find all articles by hashtag
//    List<Article> findByHashtag(
//            @Param("hashtags")
//            String hashtag
//    );


}
