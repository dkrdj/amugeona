package com.shashashark.amugeona.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleSearchParam {
    private Long boardSeq;
    private String title;
    private String content;
    private String orderBy;
    private int page;
}
