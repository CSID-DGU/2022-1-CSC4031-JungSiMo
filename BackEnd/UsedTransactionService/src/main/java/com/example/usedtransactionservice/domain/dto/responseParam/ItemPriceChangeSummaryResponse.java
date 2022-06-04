package com.example.usedtransactionservice.domain.dto.responseParam;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ItemPriceChangeSummaryResponse {

    private String itemState;

    private long itemPrice;

    private String itemTitle;

    private String itemSource;

    private String itemUrl;

}
