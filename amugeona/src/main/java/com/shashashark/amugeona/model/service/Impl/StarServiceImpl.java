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
        updateArticleStar(toEntity(starDto), 0, starDto.getRate());
        starRepository.save(toEntity(starDto));
    }

    @Override
    public void updateStar(StarUpdateParam param) {
        Star star = starRepository.findById(param.getStarSeq()).orElseThrow();
        updateArticleStar(star, star.getRate(), param.getRate());
        star.modify(param.getRate());
    }

    @Override
    public void deleteStar(Long starSeq) {
        Star star = starRepository.findById(starSeq).orElseThrow();
        updateArticleStar(star, star.getRate(), 0);
        starRepository.deleteById(starSeq);
    }

    private void updateArticleStar(Star star, Integer before, Integer after) {
        Article article = articleRepository.findById(star.getArticleSeq()).orElseThrow();
        Double sum = article.getStarRating() * article.getStarCnt();
        Integer starCnt = article.getStarCnt();
        sum -= before;
        sum += after;

        // 별점 등록
        if (before == 0)
            starCnt++;

            //별점 삭제
        else if (after == 0)
            starCnt--;

        //별점 수정은 cnt변화가 없으므로 그대로
        article.updateStar(sum / starCnt, starCnt);

    }
}
