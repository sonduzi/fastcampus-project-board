package org.fastcampus.projectboard.service;

import lombok.RequiredArgsConstructor;
import org.fastcampus.projectboard.dto.ArticleCommentDto;
import org.fastcampus.projectboard.repository.ArticleCommentRepository;
import org.fastcampus.projectboard.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComment(long articleId) {
        return List.of();
    }

    public void saveArticleComment(ArticleCommentDto dto) {
    }
}
