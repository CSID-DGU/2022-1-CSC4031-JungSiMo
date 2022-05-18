package com.example.usedtransactionservice.UsedTransactionService.domain.dto.responseParam;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Builder
@Getter
public class BrandSearchResponse {

    private String brandName;
}
