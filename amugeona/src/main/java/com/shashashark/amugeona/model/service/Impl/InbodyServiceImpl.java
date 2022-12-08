package com.shashashark.amugeona.model.service.Impl;

import com.shashashark.amugeona.model.dto.InbodyDto;
import com.shashashark.amugeona.model.dto.InbodyUpdateParam;
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
    public void updateInbody(InbodyUpdateParam param) {
        Inbody inbody = inbodyRepository.findById(param.getUserSeq()).orElseThrow();
        inbody.modify(param.getHeight(), param.getWeight(), param.getMuscle(), param.getFat());
    }

    @Override
    public void deleteInbody(Long userSeq) {
        inbodyRepository.deleteById(userSeq);
    }
}
