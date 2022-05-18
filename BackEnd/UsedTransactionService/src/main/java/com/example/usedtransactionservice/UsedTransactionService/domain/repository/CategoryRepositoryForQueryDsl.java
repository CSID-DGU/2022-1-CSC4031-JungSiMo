package com.example.usedtransactionservice.UsedTransactionService.domain.repository;

import com.example.usedtransactionservice.UsedTransactionService.domain.dto.responseParam.CategorySearchResponse;
import com.example.usedtransactionservice.UsedTransactionService.domain.entity.Category;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryForQueryDsl {

    public List<Category> findAll();

    public QueryResults<String> findAllCategory();

    public List<String> findAllCategory2();

    public List<CategorySearchResponse> findAllCategory3();

//    public String findByCategoryId(long catId);

    Optional<Category> findByCategoryId(Long catId);

//    Long findByCategoryName(String catName);
    Optional<Category> findByCategoryName(String catName);
}
