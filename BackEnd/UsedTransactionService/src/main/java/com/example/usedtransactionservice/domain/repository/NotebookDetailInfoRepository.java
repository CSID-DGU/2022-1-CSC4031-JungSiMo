package com.example.usedtransactionservice.domain.repository;

import com.example.usedtransactionservice.domain.entity.NotebookDetailInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotebookDetailInfoRepository extends JpaRepository<NotebookDetailInfo, Long> {

    List<NotebookDetailInfo> findAll();

    Optional<NotebookDetailInfo> findByItemId(Long itemId);

    Optional<NotebookDetailInfo> findByItemIdAndCategoryId(Long itemId, Long categoryId);
}
