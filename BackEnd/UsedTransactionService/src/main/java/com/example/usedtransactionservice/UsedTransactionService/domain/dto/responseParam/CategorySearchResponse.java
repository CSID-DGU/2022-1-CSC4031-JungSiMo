package com.example.usedtransactionservice.UsedTransactionService.domain.dto.responseParam;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CategorySearchResponse {

    private String categoryName;

}