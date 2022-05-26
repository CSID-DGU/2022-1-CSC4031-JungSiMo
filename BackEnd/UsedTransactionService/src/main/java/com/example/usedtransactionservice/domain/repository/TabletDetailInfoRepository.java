package com.example.usedtransactionservice.domain.repository;

import com.example.usedtransactionservice.domain.entity.TabletDetailInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TabletDetailInfoRepository extends JpaRepository<TabletDetailInfo, Long> {

    List<TabletDetailInfo> findAll();

    Optional<TabletDetailInfo> findByItemId(Long itemId);

    Optional<TabletDetailInfo> findByItemIdAndCategoryId(Long itemId, Long categoryId);
}
