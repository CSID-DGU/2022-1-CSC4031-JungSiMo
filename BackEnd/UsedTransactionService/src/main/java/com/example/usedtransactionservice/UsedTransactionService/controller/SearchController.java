package com.example.usedtransactionservice.UsedTransactionService.controller;

import com.example.usedtransactionservice.UsedTransactionService.domain.dto.requestParam.BrandSearchRequest;
import com.example.usedtransactionservice.UsedTransactionService.domain.dto.responseParam.BrandSearchResponse;
import com.example.usedtransactionservice.UsedTransactionService.domain.dto.responseParam.CategorySearchResponse;
import com.example.usedtransactionservice.UsedTransactionService.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/search")
public class SearchController {

//    @Autowired
    private final SearchService searchService;

//    @Autowired
//    public SearchController(SearchService searchService) {
//        this.searchService = searchService;
//    }


    @GetMapping("home")
    public String homepage() {
        return "home";
    }

    @GetMapping("categorySearch")
    public ResponseEntity categorySearch() {

        List<String> categoryList = searchService.categorySearch();
        log.info(String.valueOf(categoryList));

        return new ResponseEntity(CategorySearchResponse.builder().categoryName(String.valueOf(categoryList)).build(), HttpStatus.OK);

    }

    @PostMapping("brandSearch")
    public ResponseEntity brandSearch(@RequestBody BrandSearchRequest brandSearchRequest) {
        log.info(brandSearchRequest.getCategoryName());
        List<String> brandList = searchService.brandSearch(brandSearchRequest.getCategoryName());
        log.info(String.valueOf(brandList));
        return new ResponseEntity(BrandSearchResponse.builder().brandName(String.valueOf(brandList)).build(), HttpStatus.OK);
    }
}
