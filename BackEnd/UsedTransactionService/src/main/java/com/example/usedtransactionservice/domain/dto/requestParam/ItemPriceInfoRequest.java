package com.example.usedtransactionservice.domain.dto.requestParam;

import lombok.Data;

@Data
public class ItemPriceInfoRequest {

    private Long itemId;

    private Long categoryId;

    private String itemState;

    private String itemPricePeriod;

}
