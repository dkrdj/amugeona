package com.shashashark.amugeona.model.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StarUpdateParam {
    private Long starSeq;
    private Long userSeq;
    private Integer rate;
}
