package com.ktech_project2.discussion_board.repository;

import com.ktech_project2.discussion_board.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository
extends JpaRepository<Hashtag, Long> {
}
