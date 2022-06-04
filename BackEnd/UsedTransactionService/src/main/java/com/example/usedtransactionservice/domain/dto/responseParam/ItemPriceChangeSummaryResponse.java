package com.example.usedtransactionservice.domain.dto.responseParam;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ItemPriceChangeSummaryResponse {

    private String itemState;

    private LocalDate itemDate;

    private long itemMinPrice;

    private long itemAvgPrice;

    private long itemMaxPrice;

    private String itemSource;

    private String itemUrl;

}
