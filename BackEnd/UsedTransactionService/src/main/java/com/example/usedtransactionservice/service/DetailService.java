package com.example.usedtransactionservice.service;


import com.example.usedtransactionservice.domain.dto.responseParam.ItemDetailInfoResponse;
import com.example.usedtransactionservice.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DetailService {

    private final ItemRepository itemRepository;
//    private final EarphoneDetailInfoRepository earphoneDetailInfoRepository;
//    private final WatchDetailInfoRepository watchDetailInfoRepository;


    private ItemDetailInfoResponse itemDetailInfoResponse;


    // Request Param 으로 itemId 를 전달받은 경우
    // category 1 : 노트북 / 2 : 태블릿 / 3 : 스마트폰 / 4 : 이어폰 / 5 : 스마트워치
    public ItemDetailInfoResponse detailInfo(Long itemId, Long categoryId) {
        String target = mappingDetailInfo(categoryId);
        return itemDetailInfoResponse;

    }

    public String mappingDetailInfo(Long categoryId) {
        String target = "";
        if (categoryId == 1) {

        } else if (categoryId == 2) {

        } else if (categoryId == 3) {

        } else if (categoryId == 4) {

        } else if (categoryId == 5) {

        }
        return target;
    }

}
