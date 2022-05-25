package com.example.usedtransactionservice.service;

import com.example.usedtransactionservice.domain.dto.responseParam.BrandSearchResponse;
import com.example.usedtransactionservice.domain.dto.responseParam.BrandSearchResponseInterface;
import com.example.usedtransactionservice.domain.dto.responseParam.CategorySearchResponse;
import com.example.usedtransactionservice.domain.dto.responseParam.ItemSearchResponse;
import com.example.usedtransactionservice.domain.entity.Category;
import com.example.usedtransactionservice.domain.entity.Item;
import com.example.usedtransactionservice.domain.repository.CategoryRepository;
import com.example.usedtransactionservice.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SearchService {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    private CategorySearchResponse categorySearchResponse;
    private BrandSearchResponse brandSearchResponse;
    private ItemSearchResponse itemSearchResponse;

    // 카테고리 조회
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        categoryRepository.findAll().forEach(e -> categoryList.add(e));

        return categoryList;
    }

    // 브랜드 조회(카테고리 이름 선택 -> 카테고리 ID 조회 -> 브랜드 조회)
    public List<BrandSearchResponseInterface> brandSearch(String catName) {
        Optional<Category> category = categoryRepository.findByCategoryName(catName);
        Long categoryId = category.get().getCategoryId();
        List<BrandSearchResponseInterface> brandList = itemRepository.findDistinctByCategoryId(categoryId);

        return brandList;
    }

    // 상품 리스트 조회
    public List<Item> itemSearch(String catName, String itemBrand) {
        Optional<Category> category = categoryRepository.findByCategoryName(catName);
        Long categoryId = category.get().getCategoryId();
        List<Item> item = itemRepository.findByCategoryIdAndItemBrand(categoryId, itemBrand);

        return item;
    }

    // 상품 검색 -> 상품 리스트 조회 내역 내에서 검색
    public List<Item> itemSearchByKeyword(String keyword) {
        List<Item> keywordItem = itemRepository.findByItemKeyword(keyword);

        return keywordItem;
    }

}
