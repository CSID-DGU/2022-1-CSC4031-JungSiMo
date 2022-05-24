package com.example.usedtransactionservice.controller;

import com.example.usedtransactionservice.domain.dto.requestParam.BrandSearchRequest;
import com.example.usedtransactionservice.domain.dto.responseParam.BrandSearchResponse;
import com.example.usedtransactionservice.domain.dto.responseParam.BrandSearchResponseInterface;
import com.example.usedtransactionservice.domain.dto.responseParam.CategorySearchResponse;
import com.example.usedtransactionservice.domain.entity.Category;
import com.example.usedtransactionservice.domain.entity.Item;
import com.example.usedtransactionservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("home")
    public String homepage() {
        return "home";
    }

    // TODO 카테고리 조회
    @GetMapping("category")
    public ResponseEntity<List<Category>> categorySearch() {
        List<Category> category = searchService.findAll();
        return new ResponseEntity<List<Category>>(category, HttpStatus.OK);
    }

    /*
    @GetMapping("category")
    public ResponseEntity categorySearch() {
        List<Category> category = searchService.findAll();
        List<String> categoryName = new ArrayList<>();
        for (Category c : category) {
            categoryName.add(c.getCategoryName());
        }
        return new ResponseEntity(CategorySearchResponse.builder().categoryName(String.valueOf(categoryName)).build(), HttpStatus.OK);
    }
     */

    // TODO 브랜드 조회
    @PostMapping("brand")
    public ResponseEntity<List<BrandSearchResponseInterface>> brandSearch(@RequestBody BrandSearchRequest brandSearchRequest) {
        log.info(brandSearchRequest.getCategoryName());
        List<BrandSearchResponseInterface> brandList = searchService.brandSearch(brandSearchRequest.getCategoryName());
        log.info(String.valueOf(brandList));
        return new ResponseEntity<List<BrandSearchResponseInterface>> (brandList, HttpStatus.OK);
    }
}
