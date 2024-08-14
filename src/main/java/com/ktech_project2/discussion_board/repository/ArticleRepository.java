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

   // next article, previous article
   @Query("SELECT a FROM Article a WHERE a.board.id = :boardId AND a.id > :articleId ORDER BY a.id ASC")
   List<Article> findNextArticles(@Param("articleId") Long articleId, @Param("boardId") Long boardId);

    @Query("SELECT a FROM Article a WHERE a.board.id = :boardId AND a.id < :articleId ORDER BY a.id DESC")
    List<Article> findPreviousArticles(@Param("articleId") Long articleId, @Param("boardId") Long boardId);

    // Search article condition is title contain keyword
    @Query("SELECT a FROM Article a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Article> findByTitleContaining(@Param("keyword") String keyword);
    // Search article condition is content contain keyword
    @Query("SELECT a FROM Article a WHERE LOWER(a.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Article> findByContentContaining(@Param("keyword") String keyword);
    // find all articles contain keyword (in title or in content)
    @Query("SELECT a FROM Article a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(a.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Article> findByTitleOrContentContaining(@Param("keyword") String keyword);


//    // Query to find all articles by hashtag
//    List<Article> findByHashtag(
//            @Param("hashtags")
//            String hashtag
//    );


}
