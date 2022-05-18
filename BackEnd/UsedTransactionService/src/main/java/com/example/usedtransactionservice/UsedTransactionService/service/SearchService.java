package com.example.usedtransactionservice.UsedTransactionService.service;

import com.example.usedtransactionservice.UsedTransactionService.domain.dto.requestParam.BrandSearchRequest;
import com.example.usedtransactionservice.UsedTransactionService.domain.dto.responseParam.CategorySearchResponse;
import com.example.usedtransactionservice.UsedTransactionService.domain.entity.Category;
import com.example.usedtransactionservice.UsedTransactionService.domain.repository.CategoryRepository;
import com.example.usedtransactionservice.UsedTransactionService.domain.repository.CategoryRepositoryImpl;
import com.example.usedtransactionservice.UsedTransactionService.domain.repository.ItemRepository;
import com.querydsl.core.QueryResults;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

//    @Autowired
    private final CategoryRepository categoryRepository;
//    @Autowired
    private final ItemRepository itemRepository;

    private CategorySearchResponse categorySearchResponse;

    // 카테고리 조회
    public List<String> categorySearch() {
        List<String> categoryList = categoryRepository.findAllCategory2();
        log.info(String.valueOf(categoryList));

        return categoryList;

    }

    // 브랜드 조회(카테고리 이름 선택 -> 카테고리 ID 조회 -> 브랜드 조회)
/*
    public List<String> brandSearch(Long catId) {
        List<String> brandList = itemRepository.findByCategoryId(catId);
//        log.info(String.valueOf(categoryRepository.existsByCategoryId(catId)));
        log.info(String.valueOf(brandList));
        return brandList;
    }
 */

    public List<String> brandSearch(String catName) {
/*
        Long catId = categoryRepository.findByCategoryName(catName);
        List<String> brandList = itemRepository.findByCategoryId(catId);

        return brandList;
 */
        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(catName);
        log.info(String.valueOf(optionalCategory));
        List<String> brandList = itemRepository.findByCategoryId(optionalCategory.get().getCategoryId());
        return brandList;
    }

    // 상품 리스트 조회

}
