package com.example.usedtransactionservice.controller;

import com.example.usedtransactionservice.domain.dto.requestParam.ItemDetailInfoRequest;
import com.example.usedtransactionservice.domain.dto.responseParam.ItemDetailInfoResponse;
import com.example.usedtransactionservice.service.DetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.soap.Detail;

@Slf4j
@RestController
@RequestMapping("api/v1/detail")
@RequiredArgsConstructor
public class DetailController {

    private final DetailService detailService;

    // Item : itemId, categoryId, itemName, itemBrand
    // categoryId 에 따라 repository mapping

    // TODO 1. itemId 를 넘겨준 경우 - 바로 detail_info 테이블 조회
    @GetMapping("info")
    public ResponseEntity<ItemDetailInfoResponse> detailInfo(@RequestBody ItemDetailInfoRequest itemDetailInfoRequest) {
        ItemDetailInfoResponse itemDetail = detailService.detailInfo(itemDetailInfoRequest.getItemId(), itemDetailInfoRequest.getCategoryId());


        return new ResponseEntity<ItemDetailInfoResponse>(itemDetail, HttpStatus.OK);
    }

    // TODO 2. itemId 를 넘겨준 경우 - 바로 detail_info 테이블 조회

}