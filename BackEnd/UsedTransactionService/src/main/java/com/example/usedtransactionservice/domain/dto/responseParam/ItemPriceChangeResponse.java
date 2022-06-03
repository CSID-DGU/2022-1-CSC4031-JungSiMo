package com.example.usedtransactionservice.domain.dto.responseParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ItemPriceChangeResponse {

    private String itemState;

    private LocalDate itemDate;

    // 기간별 평균 가격
    private long itemPrice;


//    private List<String> highItemPrice;
//
//    private List<String> midItemPrice;
//
//    private List<String> lowItemPrice;

}
