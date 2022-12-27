package com.shashashark.amugeona.model.repository;

import com.shashashark.amugeona.model.dto.ArticleSearchParam;
import com.shashashark.amugeona.model.entity.Article;

import java.util.List;

public interface ArticleCustomRepository {
    List<Article> search(ArticleSearchParam param);
}
