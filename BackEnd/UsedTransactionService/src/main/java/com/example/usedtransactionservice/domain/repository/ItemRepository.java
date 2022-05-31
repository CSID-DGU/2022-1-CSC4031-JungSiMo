package com.example.usedtransactionservice.domain.repository;

import com.example.usedtransactionservice.domain.dto.responseParam.BrandSearchResponse;
import com.example.usedtransactionservice.domain.dto.responseParam.BrandSearchResponseInterface;
import com.example.usedtransactionservice.domain.dto.responseParam.ItemSearchResponse;
import com.example.usedtransactionservice.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAll();

    Optional<Item> findByItemId(Long itemId);

    Optional<Item> findByItemName(String itemName);

    List<BrandSearchResponseInterface> findDistinctByCategoryId(Long categoryId);

    List<Item> findByCategoryIdAndItemBrand(Long categoryId, String itemBrand);

    @Query("select i from Item i where i.categoryId = :categoryId and i.itemBrand = :itemBrand and i.itemName like %:keyword%")
    Optional<Item> findByItemKeyword(@Param("categoryId") Long categoryId, @Param("itemBrand") String itemBrand, @Param("keyword") String keyword);

}