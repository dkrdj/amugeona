package com.shashashark.amugeona.model.service;

import com.shashashark.amugeona.model.dto.InbodyDto;
import com.shashashark.amugeona.model.entity.Inbody;

import java.util.Optional;

public interface InbodyService {
    Optional<InbodyDto> selectOne(Long userSeq);

    void writeInbody(InbodyDto inbodyDto);

    void updateInbody(InbodyDto inbodyDto);

    void deleteInbody(Long userSeq);

    default InbodyDto toDto(Inbody inbody) {
        return new InbodyDto().builder()
                .userSeq(inbody.getUserSeq())
                .height(inbody.getHeight())
                .weight(inbody.getWeight())
                .muscle(inbody.getMuscle())
                .fat(inbody.getFat())
                .build();
    }

    default Inbody toEntity(InbodyDto inbodyDto) {
        return new Inbody().builder()
                .userSeq(inbodyDto.getUserSeq())
                .height(inbodyDto.getHeight())
                .weight(inbodyDto.getWeight())
                .muscle(inbodyDto.getMuscle())
                .fat(inbodyDto.getFat())
                .build();
    }
}
