package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.IngredientDto;
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
        return Optional.ofNullable(toDto(ingredientRepository.findByName(name).orElseThrow()));
    }

    @Override
    public List<IngredientDto> selectAll() {
        return ingredientRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void writeIngredient(IngredientDto ingredientDto) {
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
