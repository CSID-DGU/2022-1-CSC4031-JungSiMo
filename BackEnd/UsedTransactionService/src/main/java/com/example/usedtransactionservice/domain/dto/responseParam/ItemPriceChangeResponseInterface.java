package com.example.usedtransactionservice.domain.dto.responseParam;

import lombok.Data;

import java.util.Date;

public interface ItemPriceChangeResponseInterface {

    String itemState();

    Date getItemDate();

    Long getItemPrice();

}
