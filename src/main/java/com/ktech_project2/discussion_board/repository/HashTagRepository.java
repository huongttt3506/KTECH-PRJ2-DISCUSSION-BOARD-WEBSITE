package com.ktech_project2.discussion_board.repository;

import com.ktech_project2.discussion_board.model.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository
extends JpaRepository<HashTag, Long> {
    //find Hashtag By name
    HashTag findByName(String name);
}
