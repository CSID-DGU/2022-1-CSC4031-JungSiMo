package com.example.usedtransactionservice.domain.repository;

import com.example.usedtransactionservice.domain.entity.ItemInfo;
import com.querydsl.core.Tuple;

import java.time.LocalDate;
import java.util.List;

public interface ItemInfoCustomRepository {

    List<ItemInfo> findByItemDateBetween(LocalDate start, LocalDate end);

    List<Tuple> findByItemDateBetween2(Long itemId, LocalDate start, LocalDate end);
}
