package com.example.usedtransactionservice.controller;

import com.example.usedtransactionservice.domain.dto.requestParam.ItemDetailInfoRequest;
import com.example.usedtransactionservice.domain.dto.requestParam.ItemPriceInfoRequest;
import com.example.usedtransactionservice.service.DetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/detail")
@RequiredArgsConstructor
public class DetailController {

    private final DetailService detailService;

    @PostMapping("info")
    public ResponseEntity detailInfo(@RequestBody ItemDetailInfoRequest itemDetailInfoRequest) {

        return detailService.detailInfo(itemDetailInfoRequest.getItemId(), itemDetailInfoRequest.getCategoryId());
    }

    @PostMapping("price/change")
    public ResponseEntity priceChangeInfo(@RequestBody ItemPriceInfoRequest itemPriceInfoRequest) {
        return detailService.priceChangeInfo(itemPriceInfoRequest.getItemId(), itemPriceInfoRequest.getCategoryId(), itemPriceInfoRequest.getItemState(), itemPriceInfoRequest.getItemPricePeriod());
    }

    @PostMapping("price/summary")
    public ResponseEntity priceChangeSummaryInfo(@RequestBody ItemPriceInfoRequest itemPriceInfoRequest) {
        return detailService.priceChangeSummaryInfo(itemPriceInfoRequest.getItemId(), itemPriceInfoRequest.getCategoryId(), itemPriceInfoRequest.getItemState(), itemPriceInfoRequest.getItemPricePeriod());
    }

}