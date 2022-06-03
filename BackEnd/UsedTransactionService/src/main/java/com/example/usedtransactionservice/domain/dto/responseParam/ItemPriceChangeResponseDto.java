package com.example.usedtransactionservice.domain.dto.responseParam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ItemPriceChangeResponseDto {

    List<ItemPriceChangeResponseInterface> priceList;
}
