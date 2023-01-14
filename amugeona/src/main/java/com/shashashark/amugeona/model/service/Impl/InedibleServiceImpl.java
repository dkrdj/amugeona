package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.InedibleDto;
import com.shashashark.amugeona.model.repository.InedibleRepository;
import com.shashashark.amugeona.model.service.InedibleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InedibleServiceImpl implements InedibleService {
    private final InedibleRepository inedibleRepository;

    @Override
    public Optional<InedibleDto> selectOne(Long userSeq, Long ingredientSeq) {
        return Optional.ofNullable(toDto(inedibleRepository.findByUserSeqAndIngredientSeq(userSeq, ingredientSeq).orElseThrow()));
    }

    @Override
    public List<InedibleDto> selectAll(Long userSeq) {
        return inedibleRepository.findAllByUserSeq(userSeq).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void writeInedible(InedibleDto inedibleDto) {
        inedibleRepository.save(toEntity(inedibleDto));
    }

    @Override
    public void deleteInedible(Long userSeq, Long ingredientSeq) {
        inedibleRepository.deleteByUserSeqAndIngredientSeq(userSeq, ingredientSeq);
    }
}
