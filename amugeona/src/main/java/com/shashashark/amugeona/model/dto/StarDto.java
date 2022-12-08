package com.shashashark.amugeona.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StarDto {
    private Long starSeq;
    private Long articleSeq;
    private Long userSeq;
    private Integer rate;
}
