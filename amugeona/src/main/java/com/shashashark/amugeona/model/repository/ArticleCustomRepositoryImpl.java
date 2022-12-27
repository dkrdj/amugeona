package com.shashashark.amugeona.model.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shashashark.amugeona.model.dto.ArticleSearchParam;
import com.shashashark.amugeona.model.entity.Article;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.shashashark.amugeona.model.entity.QArticle.article;

@Repository
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {
    private final JPAQueryFactory query;

    public ArticleCustomRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }

    @Override
    public List<Article> search(ArticleSearchParam param) {
        Sort sort = Sort.by(Sort.Direction.DESC, param.getOrderBy());
        PageRequest pageRequest = PageRequest.of(param.getPage(), 10, sort);

        return query
                .select(article)
                .from(article)
                .where(containsContent(param.getContent()), containsTitle(param.getTitle()))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .orderBy(getOrderSpecifier(pageRequest))
                .fetch();
    }

    private BooleanExpression containsTitle(String title) {
        if (StringUtils.hasText(title))
            return article.title.contains(title);
        return null;
    }

    private BooleanExpression containsContent(String content) {
        if (StringUtils.hasText(content))
            return article.content.contains(content);
        return null;
    }

    private OrderSpecifier<?> getOrderSpecifier(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = Order.DESC;
                switch (order.getProperty()) {
                    case "articleSeq":
                        return new OrderSpecifier<>(direction, article.articleSeq);
                    case "articleLike":
                        return new OrderSpecifier<>(direction, article.articleLike);
                    case "viewCnt":
                        return new OrderSpecifier<>(direction, article.viewCnt);
                }
            }
        }
        return null;
    }
}
