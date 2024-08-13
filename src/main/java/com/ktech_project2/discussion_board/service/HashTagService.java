package com.ktech_project2.discussion_board.service;

import com.ktech_project2.discussion_board.model.HashTag;
import com.ktech_project2.discussion_board.repository.ArticleRepository;
import com.ktech_project2.discussion_board.repository.HashTagRepository;
import org.springframework.stereotype.Service;

@Service
public class HashTagService {
    private final HashTagRepository hashtagRepository;
    private final ArticleRepository articleRepository;

    public HashTagService(HashTagRepository hashtagRepository, ArticleRepository articleRepository) {
        this.hashtagRepository = hashtagRepository;
        this.articleRepository = articleRepository;
    }

    public HashTag saveHashTag(HashTag hashTag) {
        return hashtagRepository.save(hashTag);
    }

    public HashTag getHashTagByName(String name) {
        return hashtagRepository.findByName(name);
    }
}
