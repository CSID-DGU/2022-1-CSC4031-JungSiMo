package com.example.usedtransactionservice.domain.repository;

import com.example.usedtransactionservice.domain.dto.responseParam.ItemPriceChangeResponseInterface;
import com.example.usedtransactionservice.domain.entity.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface ItemInfoRepository extends JpaRepository<ItemInfo, Long>, ItemInfoCustomRepository {

//    List<ItemInfo> findAll();
//
////    Optional<ItemInfo> findByItemId(Long itemId);
//
//    List<ItemInfo> findByItemId(Long itemId);
//
//    List<ItemInfo> findByItemIdAndItemState(Long itemId, String itemState);

}
