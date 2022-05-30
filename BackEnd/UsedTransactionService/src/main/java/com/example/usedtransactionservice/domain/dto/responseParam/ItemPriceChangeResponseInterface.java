package com.example.usedtransactionservice.domain.dto.responseParam;

import lombok.Data;

import java.util.Date;

public interface ItemPriceChangeResponseInterface {

    Date getItemDate();

    Date setItemDate();

    Long getItemPrice();

    Long setItemPrice(Long avg);
}
