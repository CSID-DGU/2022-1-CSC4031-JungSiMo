package com.example.usedtransactionservice.domain.repository;

import com.example.usedtransactionservice.domain.entity.WatchDetailInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchDetailInfoRepository extends JpaRepository<WatchDetailInfo, Long> {

    List<WatchDetailInfo> findAll();

    Optional<WatchDetailInfo> findByItemId(Long itemId);

    Optional<WatchDetailInfo> findByItemIdAndCategoryId(Long itemId, Long categoryId);
}
