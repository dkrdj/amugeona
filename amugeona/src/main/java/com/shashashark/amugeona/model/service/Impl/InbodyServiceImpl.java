package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.InbodyDto;
import com.shashashark.amugeona.model.entity.Inbody;
import com.shashashark.amugeona.model.repository.InbodyRepository;
import com.shashashark.amugeona.model.service.InbodyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class InbodyServiceImpl implements InbodyService {
    private final InbodyRepository inbodyRepository;

    @Override
    public Optional<InbodyDto> selectOne(Long userSeq) {
        return Optional.ofNullable(toDto(inbodyRepository.findById(userSeq).orElseThrow()));
    }

    @Override
    public void writeInbody(InbodyDto inbodyDto) {
        inbodyRepository.save(toEntity(inbodyDto));
    }

    @Override
    public void updateInbody(InbodyDto inbodyDto) {
        Inbody inbody = inbodyRepository.findById(inbodyDto.getUserSeq()).orElseThrow();
        inbody.modify(inbodyDto.getHeight(), inbodyDto.getWeight(), inbodyDto.getMuscle(), inbodyDto.getFat());
    }

    @Override
    public void deleteInbody(Long userSeq) {
        inbodyRepository.deleteById(userSeq);
    }
}
