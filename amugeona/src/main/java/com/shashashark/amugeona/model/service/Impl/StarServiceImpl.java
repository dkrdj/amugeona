package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.StarDto;
import com.shashashark.amugeona.model.dto.StarUpdateParam;
import com.shashashark.amugeona.model.entity.Recipe;
import com.shashashark.amugeona.model.entity.Star;
import com.shashashark.amugeona.model.repository.RecipeRepository;
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
    private final RecipeRepository recipeRepository;

    @Override
    public Optional<StarDto> selectOne(Long userSeq, Long recipeSeq) {
        return Optional.ofNullable(toDto(starRepository.findByUserSeqAndRecipeSeq(userSeq, recipeSeq).orElseThrow()));
    }

    @Override
    public void writeStar(StarDto starDto) {
        updateRecipeStar(toEntity(starDto), 0, starDto.getRate());
        starRepository.save(toEntity(starDto));
    }

    @Override
    public void updateStar(StarUpdateParam param) {
        Star star = starRepository.findById(param.getStarSeq()).orElseThrow();
        updateRecipeStar(star, star.getRate(), param.getRate());
        star.modify(param.getRate());
    }

    @Override
    public void deleteStar(Long starSeq) {
        Star star = starRepository.findById(starSeq).orElseThrow();
        updateRecipeStar(star, star.getRate(), 0);
        starRepository.deleteById(starSeq);
    }

    private void updateRecipeStar(Star star, Integer before, Integer after) {
        Recipe recipe = recipeRepository.findById(star.getRecipeSeq()).orElseThrow();
        System.out.println(recipe.toString());
        Double sum = recipe.getStarRating() * recipe.getStarCnt();
        Integer starCnt = recipe.getStarCnt();
        sum -= before;
        sum += after;

        // 별점 등록
        if (before == 0)
            starCnt++;

            //별점 삭제
        else if (after == 0)
            starCnt--;

        //별점 수정은 cnt변화가 없으므로 그대로
        recipe.updateStar(sum / starCnt, starCnt);

    }
}
