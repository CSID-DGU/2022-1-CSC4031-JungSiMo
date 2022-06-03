package com.example.usedtransactionservice.domain.repository;

import com.example.usedtransactionservice.domain.entity.Item;
import com.example.usedtransactionservice.domain.entity.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface ItemInfoRepository extends JpaRepository<ItemInfo, Long>, ItemInfoCustomRepository {

//    List<ItemInfo> findAll();

//    Optional<ItemInfo> findByItemId(Long itemId);

//    List<ItemInfo> findByItemIdAndItemState(Long itemId, String itemState);

/*
    @Query("select i from ItemInfo i where i.itemDate between :end and :start")
    List<ItemInfo> findByItemDateBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
 */

    List<ItemInfo> findByItemDateBetween(LocalDate start, LocalDate end);

}
