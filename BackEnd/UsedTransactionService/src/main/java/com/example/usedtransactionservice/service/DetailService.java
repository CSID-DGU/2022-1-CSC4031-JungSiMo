package com.example.usedtransactionservice.service;


import com.example.usedtransactionservice.domain.dto.responseParam.ItemPriceChangeResponse;
import com.example.usedtransactionservice.domain.entity.*;
import com.example.usedtransactionservice.domain.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DetailService {

    private final ItemInfoRepositoryImpl itemInfoRepository;

    private final TabletDetailInfoRepository tabletDetailInfoRepository;
    private final SmartphoneDetailInfoRepository smartphoneDetailInfoRepository;
    private final EarphoneDetailInfoRepository earphoneDetailInfoRepository;
    private final WatchDetailInfoRepository watchDetailInfoRepository;

    // TODO 상품 상세 정보 조회
    // category 1 : 노트북 / 2 : 태블릿 / 3 : 스마트폰 / 4 : 이어폰 / 5 : 스마트워치
    public ResponseEntity detailInfo(Long itemId, Long categoryId) {
        ResponseEntity resultResponseEntity = null;
        if (categoryId == 1) {   // 노트북

        } else if (categoryId == 2) {   // 태블릿
            Optional<TabletDetailInfo> tabletDetailInfo = tabletDetailInfoRepository.findByItemId(itemId);
            resultResponseEntity = new ResponseEntity(tabletDetailInfo, HttpStatus.OK);

        } else if (categoryId == 3) {   // 스마트폰
            Optional<SmartphoneDetailInfo> smartphoneDetailInfo = smartphoneDetailInfoRepository.findByItemId(itemId);
            resultResponseEntity = new ResponseEntity(smartphoneDetailInfo, HttpStatus.OK);

        } else if (categoryId == 4) {   // 이어폰
            Optional<EarphoneDetailInfo> earphoneDetailInfo = earphoneDetailInfoRepository.findByItemId(itemId);
            resultResponseEntity = new ResponseEntity(earphoneDetailInfo, HttpStatus.OK);

        } else if (categoryId == 5) {   // 스마트워치
            Optional<WatchDetailInfo> watchDetailInfo = watchDetailInfoRepository.findByItemId(itemId);
            resultResponseEntity = new ResponseEntity(watchDetailInfo, HttpStatus.OK);
        }

        return resultResponseEntity;

    }

    // TODO 상품 가격 변동 조회 - start : 이전 날짜 / end : 최근 날짜
    public ResponseEntity priceChangeInfo(Long itemId, String itemPricePeriod) {
        ResponseEntity resultResponseEntity = null;
        String highState = "상";
        String midState = "중";
        String lowState = "하";

        // 상품 상태 : 상
        List<ItemInfo> highTmpList = itemInfoRepository.findByItemIdAndItemState(itemId, highState);
        for (ItemInfo i : highTmpList) {
            System.out.println(" 날짜 : "  + i.getItemDate() + " 가격 : "  + i.getItemPrice());
        }

        // 상품 상태 : 중
        List<ItemInfo> midTmpList = itemInfoRepository.findByItemIdAndItemState(itemId, midState);
        for (ItemInfo i : midTmpList) {
            System.out.println(" 날짜 : "  + i.getItemDate() + " 가격 : "  + i.getItemPrice());
        }

        // 상품 상태 : 하
        List<ItemInfo> lowTmpList = itemInfoRepository.findByItemIdAndItemState(itemId, lowState);
        for (ItemInfo i : lowTmpList) {
            System.out.println(" 날짜 : "  + i.getItemDate() + " 가격 : "  + i.getItemPrice());
        }


        List<List<ItemPriceChangeResponse>> list = itemInfoRepository.priceChangeInfo(itemId, itemPricePeriod);

        return new ResponseEntity(list, HttpStatus.OK);
    }

    // TODO 상품 가격 변동 요약 정보 조회(최고가/평균가/최저가)
    public ResponseEntity priceChangeSummaryInfo(Long itemId, String itemPricePeriod) {
        ResponseEntity resultResponseEntity = null;
        String highState = "상";
        String midState = "중";
        String lowState = "하";

        // 상품 상태 : 상
        List<ItemInfo> highTmpList = itemInfoRepository.findByItemIdAndItemState(itemId, highState);

        // 상품 상태 : 중
        List<ItemInfo> midTmpList = itemInfoRepository.findByItemIdAndItemState(itemId, midState);

        // 상품 상태 : 하
        List<ItemInfo> lowTmpList = itemInfoRepository.findByItemIdAndItemState(itemId, lowState);

        return resultResponseEntity;
    }

}
