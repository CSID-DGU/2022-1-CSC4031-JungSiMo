package com.example.usedtransactionservice.UsedTransactionService.domain.repository;

import com.example.usedtransactionservice.UsedTransactionService.domain.entity.Category;
import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryForQueryDsl {

    List<Category> findAll();

    boolean existsByCategoryId(Long categoryId);

//    public List<Category> findAll() {
//        return em.createQuery("select m from Category m", Category.class)
//                .getResultList();
//    }

}
