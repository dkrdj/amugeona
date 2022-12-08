package com.shashashark.amugeona.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InbodyDto {
    private Long userSeq;

    private Double height;
    private Double weight;
    private Double muscle;
    private Double fat;
}
