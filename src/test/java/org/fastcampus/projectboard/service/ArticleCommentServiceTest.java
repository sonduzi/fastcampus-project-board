package org.fastcampus.projectboard.service;

import org.fastcampus.projectboard.domain.Article;
import org.fastcampus.projectboard.domain.ArticleComment;
import org.fastcampus.projectboard.domain.UserAccount;
import org.fastcampus.projectboard.dto.ArticleCommentDto;
import org.fastcampus.projectboard.repository.ArticleCommentRepository;
import org.fastcampus.projectboard.repository.ArticleRepository;
import org.fastcampus.projectboard.repository.UserAccountRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;
    @Mock private ArticleCommentRepository articleCommentRepository;
    @Mock private ArticleRepository articleRepository;
    @Mock private UserAccountRepository userAccountRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnsComments(){
        //Given
        Long articleId = 1L;
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("uno", "pw", null, null, null));
        Article article = Article.of(userAccount, "new article", "new content", "#spring");
        given(articleRepository.findById(articleId)).willReturn(
                Optional.of(Article.of(userAccount,"title", "content", "#java")));

        //When
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(1L);

        //Then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComment() {
        // Given
         given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        // When
        sut.saveArticleComment(ArticleCommentDto.of(LocalDateTime.now(), "Duzi", null, null, "content"));

        // Then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }
}