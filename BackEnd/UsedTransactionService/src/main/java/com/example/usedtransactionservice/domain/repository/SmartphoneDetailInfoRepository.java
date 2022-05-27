package com.example.usedtransactionservice.domain.repository;

import com.example.usedtransactionservice.domain.entity.SmartphoneDetailInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SmartphoneDetailInfoRepository extends JpaRepository<SmartphoneDetailInfo, Long> {

    List<SmartphoneDetailInfo> findAll();

    Optional<SmartphoneDetailInfo> findByItemId(Long itemId);

    Optional<SmartphoneDetailInfo> findByItemIdAndCategoryId(Long itemId, Long categoryId);
}
