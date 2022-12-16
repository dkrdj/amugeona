package com.shashashark.amugeona.model.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateParam {
    private Long userSeq;
    private Long commentSeq;
    private String content;
}
