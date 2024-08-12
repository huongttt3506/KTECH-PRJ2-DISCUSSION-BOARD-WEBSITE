package com.ktech_project2.discussion_board.repository;

import com.ktech_project2.discussion_board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
