package com.example.usedtransactionservice.service;


import com.example.usedtransactionservice.domain.dto.responseParam.ItemPriceChangeInfoResponse;
import com.example.usedtransactionservice.domain.entity.EarphoneDetailInfo;
import com.example.usedtransactionservice.domain.entity.WatchDetailInfo;
import com.example.usedtransactionservice.domain.repository.EarphoneDetailInfoRepository;
import com.example.usedtransactionservice.domain.repository.ItemRepository;
import com.example.usedtransactionservice.domain.repository.WatchDetailInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DetailService {

    private final ItemRepository itemRepository;
    private final EarphoneDetailInfoRepository earphoneDetailInfoRepository;
    private final WatchDetailInfoRepository watchDetailInfoRepository;

    private ItemPriceChangeInfoResponse itemPriceChangeInfoResponse;

    // TODO 상품 상세 정보 조회
    // category 1 : 노트북 / 2 : 태블릿 / 3 : 스마트폰 / 4 : 이어폰 / 5 : 스마트워치
    public ResponseEntity detailInfo(Long itemId, Long categoryId) {
        ResponseEntity resultResponseEntity = null;
        if (categoryId == 1) {   // 노트북

        } else if (categoryId == 2) {   // 태블릿

        } else if (categoryId == 3) {   // 스마트폰

        } else if (categoryId == 4) {   // 이어폰
            Optional<EarphoneDetailInfo> earphoneDetailInfo = earphoneDetailInfoRepository.findByItemId(itemId);
            resultResponseEntity = new ResponseEntity(earphoneDetailInfo, HttpStatus.OK);

        } else if (categoryId == 5) {   // 스마트워치
            Optional<WatchDetailInfo> watchDetailInfo = watchDetailInfoRepository.findByItemId(itemId);
            resultResponseEntity = new ResponseEntity(watchDetailInfo, HttpStatus.OK);
        }

        return resultResponseEntity;

    }

    // TODO 상품 가격 변동 조회
    public ResponseEntity priceChangeInfo(Long itemId, Long categoryId, String itemState, String itemPricePeriod) {
        ResponseEntity resultResponseEntity = null;

        return resultResponseEntity;
    }

}
