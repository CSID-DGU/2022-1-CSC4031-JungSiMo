package com.example.usedtransactionservice.UsedTransactionService.domain.dto.responseParam;

import lombok.Data;

@Data
public class ItemSearchResponse {

    private Long itemId;

    private String itemName;

    private Long categoryId;

    private String brandName;

}
