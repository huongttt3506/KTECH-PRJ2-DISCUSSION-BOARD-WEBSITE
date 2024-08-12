package com.ktech_project2.discussion_board.repository;

import com.ktech_project2.discussion_board.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository
extends JpaRepository<Comment, Long> {
}
