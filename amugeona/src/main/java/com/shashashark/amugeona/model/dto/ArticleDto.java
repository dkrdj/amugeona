package com.shashashark.amugeona.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long articleSeq;
    private Long userSeq;
    private String nickname;
    private Long boardSeq;
    private String title;
    private String content;
    private Double starRating;
    private Integer starCnt;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
