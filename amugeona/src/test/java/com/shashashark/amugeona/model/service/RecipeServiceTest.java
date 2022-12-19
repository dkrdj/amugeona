//package com.shashashark.amugeona.model.service;
//
//import com.shashashark.amugeona.model.dto.RecipeDto;
//import com.shashashark.amugeona.model.entity.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//class RecipeServiceTest {
//
//    @PersistenceContext
//    EntityManager em;
//
//    @Autowired
//    RecipeService recipeService;
//
//    @Test
//    @Transactional
//    void selectAll() {
//        //given
//        for (int i = 1; i <= 10; i++) {
//            Recipe recipe = new Recipe().builder()
//                    .recipeSeq((long) i)
//                    .title("요리명" + i)
//                    .thumbnail("thumb")
//                    .starRating(3.0)
//                    .starCnt(0)
//                    .build();
//            Ingredient ingredient = new Ingredient().builder()
//                    .ingredientSeq((long) i)
//                    .name("재료" + i)
//                    .build();
//            em.persist(recipe);
//            em.persist(ingredient);
//        }
//        for (int i = 1; i <= 10; i++) {
//            for (int j = i; j < i + 5 && j <= 10; j++) {
//                RecipeIngredient recipeIngredient = new RecipeIngredient().builder()
//                        .ingredientSeq((long) j)
//                        .recipeSeq((long) i)
//                        .build();
//                em.persist(recipeIngredient);
//            }
//        }
//        for (int i = 1; i <= 8; i++) {
//            User user = new User().builder()
//                    .userSeq((long) i)
//                    .userId("test" + i)
//                    .password("test")
//                    .email("test@test.com")
//                    .name("김테슷" + i)
//                    .nickname("테스트" + i)
//                    .build();
//            em.persist(user);
//            for (int j = i; j < i + 3; j++) {
//                Inedible inedible = new Inedible().builder()
//                        .ingredientSeq((long) j)
//                        .userSeq((long) i)
//                        .build();
//                em.persist(inedible);
//            }
//        }
//
//        //when
//        List<RecipeDto>[] findAllResult = new ArrayList[9];
//        List<RecipeDto>[] searchTitleResult = new ArrayList[9];
//        for (int i = 1; i <= 8; i++) {
//            findAllResult[i] = recipeService.selectAll((long) i, "recipeSeq", 0);
//            searchTitleResult[i] = recipeService.searchTitle((long) i, "recipeSeq", String.valueOf(i), 0);
//        }
//
//        //then
//        for (int i = 1; i <= 8; i++) {
//            for (int j = 0; j < findAllResult[i].size(); j++) {
//                if (i + j + 3 <= 10)
//                    assertEquals(findAllResult[i].get(j).getRecipeSeq(), (long) i + j + 3);
//                else
//                    assertEquals(findAllResult[i].get(j).getRecipeSeq(), (long) i + j + 3 - 10);
//            }
//        }
//    }
//
//    @Test
//    void searchTitle() {
//    }
//}