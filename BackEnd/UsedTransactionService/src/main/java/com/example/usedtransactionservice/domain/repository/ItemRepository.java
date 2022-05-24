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

    // categoryName 으로 categoryId 찾고 -> categoryId에 해당하는 itemBrand 중복 제거
//    @Query("select i.itemBrand from Item i group by where i.categoryId = :categoryId")
//    List<BrandSearchResponseInterface> findByCategoryId(@Param("categoryId") Long categoryId);
    List<BrandSearchResponseInterface> findByCategoryId(Long categoryId);

    List<Item> findByCategoryIdAndItemBrand(Long categoryId, String itemBrand);

}