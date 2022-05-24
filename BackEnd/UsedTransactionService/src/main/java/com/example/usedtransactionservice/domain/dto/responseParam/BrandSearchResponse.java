package com.example.usedtransactionservice.domain.dto.responseParam;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class BrandSearchResponse {

    private String itemBrand;
}
