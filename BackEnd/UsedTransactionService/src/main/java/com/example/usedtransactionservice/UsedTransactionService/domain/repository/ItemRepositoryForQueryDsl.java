package com.example.usedtransactionservice.UsedTransactionService.domain.repository;

import com.example.usedtransactionservice.UsedTransactionService.domain.entity.Item;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ItemRepositoryForQueryDsl {

    public List<String> findByCategoryId(Long catId);

    public List<Item> findByCategoryIdAndBrandName(Long catId, String brandNm, Pageable pageable);

    public Page<Item> findItemListByKeyword(BooleanBuilder booleanBuilder, Pageable pageable, Sort sort, String keyword);
}
