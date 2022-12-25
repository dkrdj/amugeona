package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.ArticleDto;
import com.shashashark.amugeona.model.entity.Article;
import com.shashashark.amugeona.model.param.ArticleUpdateParam;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Optional<ArticleDto> selectOne(Long articleSeq);


    List<ArticleDto> selectAll(Long boardSeq, String orderBy, int page);

    List<ArticleDto> selectAllList(String orderBy, int page);

    List<ArticleDto> searchTitle(Long boardSeq, String title, String orderBy, int page);

    List<ArticleDto> searchContent(Long boardSeq, String content, String orderBy, int page);

    void writeArticle(ArticleDto articleDto);

    void updateArticle(ArticleUpdateParam param);

    void deleteArticle(Long articleSeq);

    void updateLike(Long articleSeq);

    default ArticleDto toDto(Article article) {
        return new ArticleDto().builder()
                .articleSeq(article.getArticleSeq())
                .userSeq(article.getUserSeq())
                .nickname(article.getUser().getNickname())
                .boardSeq(article.getBoardSeq())
                .title(article.getTitle())
                .content(article.getContent())
                .articleLike(article.getArticleLike())
                .viewCnt(article.getViewCnt())
                .info(article.getInfo())
                .createdAt(article.getCreatedAt())
                .modifiedAt(article.getModifiedAt())
                .build();
    }

    default Article toEntity(ArticleDto articleDto){
        return new Article().builder()
                .userSeq(articleDto.getUserSeq())
                .boardSeq(articleDto.getBoardSeq())
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .info(articleDto.getInfo())
                .build();
    }
}
