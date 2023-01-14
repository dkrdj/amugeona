package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.RecipeDto;
import com.shashashark.amugeona.model.entity.Recipe;
import com.shashashark.amugeona.model.repository.RecipeRepository;
import com.shashashark.amugeona.model.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Override
    public Optional<RecipeDto> selectOne(Long recipeSeq) {
        return Optional.ofNullable(toDto(recipeRepository.findById(recipeSeq).orElseThrow()));
    }

    @Override
    public List<RecipeDto> selectAll(Long userSeq, String orderBy, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, orderBy);
        PageRequest pageRequest = PageRequest.of(page, 10, sort);
        return recipeRepository.findAll(userSeq, pageRequest).stream().map(this::toDtoList).collect(Collectors.toList());
    }

    @Override
    public List<RecipeDto> search(Long userSeq, String orderBy, String title, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, orderBy);
        PageRequest pageRequest = PageRequest.of(page, 10, sort);
        return recipeRepository.searchTitle(title, userSeq, pageRequest).stream().map(this::toDtoList).collect(Collectors.toList());
    }

    private RecipeDto toDtoList(Recipe recipe) {
        return RecipeDto.builder()
                .recipeSeq(recipe.getRecipeSeq())
                .title(recipe.getTitle())
                .thumbnail(recipe.getThumbnail())
                .starRating(recipe.getStarRating())
                .starCnt(recipe.getStarCnt())
                .build();
    }
}
