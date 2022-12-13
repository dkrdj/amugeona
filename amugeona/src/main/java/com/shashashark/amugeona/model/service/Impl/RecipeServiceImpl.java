package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.RecipeDto;
import com.shashashark.amugeona.model.repository.RecipeRepository;
import com.shashashark.amugeona.model.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Override
    public Optional<RecipeDto> selectOne(Long recipeSeq) {
        return Optional.ofNullable(toDto(recipeRepository.findById(recipeSeq).orElseThrow()));
    }

    @Override
    public List<RecipeDto> selectAll() {
        return recipeRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }
}
