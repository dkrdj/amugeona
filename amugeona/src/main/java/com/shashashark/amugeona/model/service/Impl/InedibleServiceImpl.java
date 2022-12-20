package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.InedibleDto;
import com.shashashark.amugeona.model.entity.Inedible;
import com.shashashark.amugeona.model.repository.InedibleRepository;
import com.shashashark.amugeona.model.service.InedibleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
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
        Inedible inedible = toEntity(inedibleDto);
        System.out.println(inedible);
        inedibleRepository.save(inedible);
    }

    @Override
    public void deleteInedible(Long userSeq, Long ingredientSeq) {
        inedibleRepository.deleteByUserSeqAndIngredientSeq(userSeq, ingredientSeq);
    }
}
