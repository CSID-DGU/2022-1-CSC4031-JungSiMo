package com.example.usedtransactionservice.domain.repository;

import com.example.usedtransactionservice.domain.entity.ItemInfo;
import com.example.usedtransactionservice.domain.entity.QItemInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j2
@Repository
public class ItemInfoRepositoryImpl implements ItemInfoCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public ItemInfoRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    QItemInfo itemInfo = QItemInfo.itemInfo;

    public List<ItemInfo> findAll() {
        return jpaQueryFactory.selectFrom(itemInfo)
                .fetch();
    }

    public List<ItemInfo> findByItemIdAndItemState(Long itemId, String itemState) {
        return jpaQueryFactory.selectFrom(itemInfo)
                .where(itemInfo.itemId.eq(itemId)
                    .and(itemInfo.itemState.eq(itemState)))
                .fetch();

    }



}
