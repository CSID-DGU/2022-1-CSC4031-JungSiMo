package com.example.usedtransactionservice.domain.dto.requestParam;

import lombok.Data;

@Data
public class ItemSearchRequest {

    private String categoryName;

    private String itemBrand;
}
