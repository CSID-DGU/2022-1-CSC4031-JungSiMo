package com.example.usedtransactionservice.domain.dto.responseParam;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class ItemSearchResponse {

    private Long itemId;

    private String itemName;

    private Long categoryId;

    private String brandName;

}
