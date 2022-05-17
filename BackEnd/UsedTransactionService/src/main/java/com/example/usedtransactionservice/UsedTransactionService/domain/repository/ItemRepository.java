package com.example.usedtransactionservice.UsedTransactionService.domain.repository;

import com.example.usedtransactionservice.UsedTransactionService.domain.entity.Item;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ItemRepository {

    public List<Item> findAll(Predicate predicate, Sort sort);

}
