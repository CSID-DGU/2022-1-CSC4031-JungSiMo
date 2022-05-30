package com.example.usedtransactionservice.domain.dto.responseParam;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ItemPriceChangeResponse {

//    private List<String> highItemPrice;
//
//    private List<String> midItemPrice;
//
//    private List<String> lowItemPrice;

    private List<String> highItemPrice;

    private List<String> midItemPrice;

    private List<String> lowItemPrice;

}
