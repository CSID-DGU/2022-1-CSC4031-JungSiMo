package com.example.usedtransactionservice.UsedTransactionService.domain.repository;

import com.example.usedtransactionservice.UsedTransactionService.domain.entity.Category;
import com.example.usedtransactionservice.UsedTransactionService.domain.entity.QCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    // TODO 카테고리 ID -> 카테고리 이름 조회
    public List<Category> findByCategoryId(Long catId) {
        QCategory category = QCategory.category;
        List<Category> categoryList = queryFactory
                .selectFrom(category)
                .where(category.categoryId.eq(catId))
                .fetch();
        return categoryList;

    }

}
