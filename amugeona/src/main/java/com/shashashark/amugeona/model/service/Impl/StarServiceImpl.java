package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.StarDto;
import com.shashashark.amugeona.model.dto.StarUpdateParam;
import com.shashashark.amugeona.model.entity.Article;
import com.shashashark.amugeona.model.entity.Star;
import com.shashashark.amugeona.model.repository.ArticleRepository;
import com.shashashark.amugeona.model.repository.StarRepository;
import com.shashashark.amugeona.model.service.StarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StarServiceImpl implements StarService {
    private final StarRepository starRepository;
    private final ArticleRepository articleRepository;

    @Override
    public Optional<StarDto> selectOne(Long userSeq, Long articleSeq) {
        return Optional.ofNullable(toDto(starRepository.findByUserSeqAndArticleSeq(userSeq, articleSeq)));
    }

    @Override
    public void writeStar(StarDto starDto) {
        Article article = articleRepository.findById(starDto.getArticleSeq()).orElseThrow();
        Double starRating = (article.getStarRating() * article.getStarCnt() + starDto.getRate()) / (article.getStarCnt() + 1);
        article.updateStar(starRating, article.getStarCnt() + 1);
        starRepository.save(toEntity(starDto));
    }

    @Override
    public void updateStar(StarUpdateParam param) {
        Star star = starRepository.findById(param.getStarSeq()).orElseThrow();
        Article article = articleRepository.findById(star.getArticleSeq()).orElseThrow();
        Double starRating = (article.getStarRating() * article.getStarCnt() - star.getRate() + param.getRate()) / article.getStarCnt();
        article.updateStar(starRating, article.getStarCnt());
        star.modify(param.getRate());
    }

    @Override
    public void deleteStar(Long starSeq) {
        starRepository.deleteById(starSeq);
    }
}
