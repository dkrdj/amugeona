package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.ArticleLikeDto;
import com.shashashark.amugeona.model.repository.ArticleLikeRepository;
import com.shashashark.amugeona.model.service.ArticleLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleArticleLikeServiceImpl implements ArticleLikeService {
    private final ArticleLikeRepository articleLikeRepository;

    @Override
    public boolean findOne(Long userSeq, Long articleSeq) {
        return articleLikeRepository.findByUserSeqAndArticleSeq(userSeq, articleSeq).isPresent();
    }

    @Override
    public void writeLike(ArticleLikeDto articleLikeDto) {
        articleLikeRepository.save(toEntity(articleLikeDto));
    }

    @Override
    public void deleteLike(Long userSeq, Long articleSeq) {
        articleLikeRepository.deleteByUserSeqAndArticleSeq(userSeq, articleSeq);
    }
}
