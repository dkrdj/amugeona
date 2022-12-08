package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.IngredientDto;
import com.shashashark.amugeona.model.entity.Ingredient;
import com.shashashark.amugeona.model.repository.IngredientRepository;
import com.shashashark.amugeona.model.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Optional<IngredientDto> selectOne(String name) {
        Ingredient ingredient = ingredientRepository.findByName(name).orElseThrow();
        return Optional.ofNullable(toDto(ingredientRepository.findById(ingredient.getIngredientSeq()).orElseThrow()));
    }

    @Override
    public List<IngredientDto> selectAll() {
        return ingredientRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void writeIngredient(IngredientDto ingredientDto) {
        //존재하지 않으면 넣기
        if (ingredientRepository.findByName(ingredientDto.getName()).isEmpty())
            ingredientRepository.save(toEntity(ingredientDto));
    }

//    @Override
//    public void updateIngredient(IngredientDto ingredientDto) {
//        Ingredient ingredient = ingredientRepository.findById(ingredientDto.getIngredientSeq()).orElseThrow();
//        ingredient.modify(ingredientDto.getName());
//    }
//
//    @Override
//    public void deleteIngredient(Long ingredientSeq) {
//        ingredientRepository.deleteById(ingredientSeq);
//    }
}
