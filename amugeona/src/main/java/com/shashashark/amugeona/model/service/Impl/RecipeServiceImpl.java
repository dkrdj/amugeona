package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.RecipeDto;
import com.shashashark.amugeona.model.entity.Inedible;
import com.shashashark.amugeona.model.entity.Recipe;
import com.shashashark.amugeona.model.entity.RecipeIngredient;
import com.shashashark.amugeona.model.repository.InedibleRepository;
import com.shashashark.amugeona.model.repository.RecipeRepository;
import com.shashashark.amugeona.model.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final InedibleRepository inedibleRepository;

    @Override
    public Optional<RecipeDto> selectOne(Long recipeSeq) {
        return Optional.ofNullable(toDto(recipeRepository.findById(recipeSeq).orElseThrow()));
    }

    @Override
    public List<RecipeDto> selectAll(Long userSeq) {
        return recipeRepository.findAll().stream()
                .filter(recipe -> !isContain(
                        recipe.getRecipeIngredients().stream().map(RecipeIngredient::getIngredientSeq).collect(Collectors.toList()),
                        inedibleRepository.findAllByUserSeq(userSeq).stream().map(Inedible::getIngredientSeq).collect(Collectors.toList())))
                .map(this::toDtoList).collect(Collectors.toList());
    }

    private RecipeDto toDtoList(Recipe recipe) {
        return new RecipeDto().builder()
                .recipeSeq(recipe.getRecipeSeq())
                .title(recipe.getTitle())
                .thumbnail(recipe.getThumbnail())
                .build();
    }

    private boolean isContain(List<Long> list1, List<Long> list2) {
        for (Long seq : list1) {
            if (list2.contains(seq))
                return true;
        }
        return false;
    }
}
