package com.ktech_project2.discussion_board.repository;

import com.ktech_project2.discussion_board.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository
extends JpaRepository<Article, Long> {

}
