package com.example.usedtransactionservice.domain.dto.responseParam;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ItemPriceChangeSummaryResponseDto {

    List<ItemPriceChangeSummaryResponse> maxPriceItem;

    List<ItemPriceChangeSummaryResponse> avgPriceItem;

    List<ItemPriceChangeSummaryResponse> minPriceItem;

}
