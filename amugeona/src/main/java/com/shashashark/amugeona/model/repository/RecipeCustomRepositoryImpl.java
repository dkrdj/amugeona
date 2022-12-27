package com.shashashark.amugeona.model.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shashashark.amugeona.model.entity.Recipe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.shashashark.amugeona.model.entity.QInedible.inedible;
import static com.shashashark.amugeona.model.entity.QRecipe.recipe;
import static com.shashashark.amugeona.model.entity.QRecipeIngredient.recipeIngredient;

@Repository
public class RecipeCustomRepositoryImpl implements RecipeCustomRepository {
    private final JPAQueryFactory query;

    public RecipeCustomRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }

    @Override
    public List<Recipe> findAll(Long userSeq, Pageable pageable) {
        return query
                .select(recipe)
                .from(recipe)
                .where(recipe.recipeSeq.notIn(
                        JPAExpressions
                                .select(recipeIngredient.recipeSeq)
                                .from(recipeIngredient)
                                .where(recipeIngredient.ingredientSeq.in(
                                        JPAExpressions
                                                .select(inedible.ingredientSeq)
                                                .from(inedible)
                                                .where(inedible.userSeq.eq(userSeq))
                                ))
                ))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable))
                .fetch();
    }


    @Override
    public List<Recipe> searchTitle(String title, Long userSeq, Pageable pageable) {
        return query
                .select(recipe)
                .from(recipe)
                .where(recipe.recipeSeq.notIn(
                        JPAExpressions
                                .select(recipeIngredient.recipeSeq)
                                .from(recipeIngredient)
                                .where(recipeIngredient.ingredientSeq.in(
                                        JPAExpressions
                                                .select(inedible.ingredientSeq)
                                                .from(inedible)
                                                .where(inedible.userSeq.eq(userSeq))
                                ))
                ), recipe.title.contains(title))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable))
                .fetch();
    }

    private OrderSpecifier<?> getOrderSpecifier(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case "recipeSeq":
                        return new OrderSpecifier<>(direction, recipe.recipeSeq);
                    case "starRating":
                        return new OrderSpecifier<>(direction, recipe.starRating);
                    case "starCnt":
                        return new OrderSpecifier<>(direction, recipe.starCnt);
                }
            }
        }
        return null;
    }

}
