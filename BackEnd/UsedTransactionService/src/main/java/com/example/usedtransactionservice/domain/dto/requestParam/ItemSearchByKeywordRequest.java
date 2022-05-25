package com.example.usedtransactionservice.domain.dto.requestParam;

import lombok.Data;

@Data
public class ItemSearchByKeywordRequest {

    private String categoryName;

    private String itemBrand;

    private String keyword;

}
