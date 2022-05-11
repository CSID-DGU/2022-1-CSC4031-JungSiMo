package com.example.usedtransactionservice.UsedTransactionService.domain.repository;

import com.example.usedtransactionservice.UsedTransactionService.domain.entity.Item;
import com.example.usedtransactionservice.UsedTransactionService.domain.entity.QItem;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl{
    private final JPAQueryFactory queryFactory;

    QItem item = QItem.item;

    // TODO CategoryId 에 해당하는 BrandName 조회
    public List<String> findByCategoryId(Long catId) {
        List<String> itemBrandList = queryFactory
                .from(item)
                .select(item.brandName)
                .where(item.categoryId.eq(catId))
                .fetch();

        return itemBrandList;
    }

    // TODO CategoryId 와 BrandName 을 선택했을 때의 상품 리스트
    public List<Item> findByCategoryIdAndBrandName(Long catId, String brandNm, Pageable pageable) {
        List<Item> itemList = queryFactory
                .selectFrom(item)
                .where(item.categoryId.eq(catId)
                        .and(item.brandName.eq(brandNm)))
                .fetch();

        return itemList;

    }


}
