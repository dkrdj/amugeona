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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StarServiceImpl implements StarService {
    private final StarRepository starRepository;
    private final RecipeRepository recipeRepository;

    @Override
    public List<StarDto> selectAll(Long recipeSeq) {
        return starRepository.findByRecipeSeq(recipeSeq).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void writeStar(StarDto starDto) {
        updateRecipeStar(toEntity(starDto), 0, starDto.getRate());
        starRepository.save(toEntity(starDto));
    }

    @Override
    @Transactional
    public void updateStar(StarUpdateParam param) {
        Star star = starRepository.findByUserSeqAndRecipeSeq(param.getUserSeq(), param.getRecipeSeq()).orElseThrow();
        updateRecipeStar(star, star.getRate(), param.getRate());
        star.modify(param.getRate());
    }

    @Override
    @Transactional
    public void deleteStar(Long userSeq, Long recipeSeq) {
        Star star = starRepository.findByUserSeqAndRecipeSeq(userSeq, recipeSeq).orElseThrow();
        updateRecipeStar(star, star.getRate(), 0);
        starRepository.delete(star);
    }

    private void updateRecipeStar(Star star, Integer before, Integer after) {
        Recipe recipe = recipeRepository.findById(star.getRecipeSeq()).orElseThrow();
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
