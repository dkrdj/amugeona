package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.IngredientDto;
import com.shashashark.amugeona.model.repository.IngredientRepository;
import com.shashashark.amugeona.model.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public List<IngredientDto> selectAll(String name) {
        PageRequest pageRequest = PageRequest.of(0, 30);
        return ingredientRepository.findByNameContaining(name, pageRequest).stream().map(this::toDto).collect(Collectors.toList());
    }

}
