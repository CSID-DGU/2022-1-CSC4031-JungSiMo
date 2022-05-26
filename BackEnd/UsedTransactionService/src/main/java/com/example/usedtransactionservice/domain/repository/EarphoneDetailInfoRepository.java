package com.example.usedtransactionservice.domain.repository;

import com.example.usedtransactionservice.domain.entity.EarphoneDetailInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EarphoneDetailInfoRepository extends JpaRepository<EarphoneDetailInfo, Long> {

    List<EarphoneDetailInfo> findAll();

    Optional<EarphoneDetailInfo> findByItemId(Long itemId);

    Optional<EarphoneDetailInfo> findByItemIdAndCategoryId(Long itemId, Long categoryId);
}
