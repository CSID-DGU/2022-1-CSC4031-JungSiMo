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
//    @Query("select distinct i from Item i where i.categoryId = :categoryId")
//    List<BrandSearchResponseInterface> findDistinctByCategoryId(@Param("categoryId") Long categoryId);
    List<BrandSearchResponseInterface> findDistinctByCategoryId(Long categoryId);

    List<Item> findByCategoryIdAndItemBrand(Long categoryId, String itemBrand);

//    @Query("select i from Item i where i.itemName like %:keyword% and i.categoryId = :categoryId and i.itemBrand = :itemBrand")
//    List<Item> findByItemKeyword(@Param("keyword") Long categoryId, String itemBrand, String keyword);
@Query("select i from Item i where i.itemName like %:keyword%")
List<Item> findByItemKeyword(@Param("keyword") String keyword);
}