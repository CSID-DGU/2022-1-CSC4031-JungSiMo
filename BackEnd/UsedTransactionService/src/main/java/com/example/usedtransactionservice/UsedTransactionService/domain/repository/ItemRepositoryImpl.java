package com.example.usedtransactionservice.UsedTransactionService.domain.repository;

import com.example.usedtransactionservice.UsedTransactionService.domain.entity.Item;
import com.example.usedtransactionservice.UsedTransactionService.domain.entity.QItem;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
//@RequiredArgsConstructor
public class ItemRepositoryImpl extends QuerydslRepositorySupport {

    @Autowired
    private JPAQueryFactory queryFactory;

    QItem item = QItem.item;

    public ItemRepositoryImpl() {
        super(Item.class);
    }

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

    // TODO 검색어 입력을 통한 상품 검색(카테고리, 브랜드 필터링)
    public Page<Item> findItemListByKeyword(BooleanBuilder booleanBuilder, Pageable pageable, Sort sort, String keyword) {
        JPAQuery<Item> query = queryFactory
                .selectFrom(item)
                .where(eqItemName(keyword));

        List<Item> items = this.getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<Item>(items, pageable, query.stream().count());

    }

    private BooleanExpression eqItemName(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return item.itemName.contains(keyword);
    }


}
