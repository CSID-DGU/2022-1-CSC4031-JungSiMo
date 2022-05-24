//package com.example.usedtransactionservice.UsedTransactionService.domain.repository;
//
//import com.example.usedtransactionservice.UsedTransactionService.domain.entity.Category;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//import java.util.Optional;
//
//
//public class JpaCategoryRepository implements CategoryRepository {
//
//    private final EntityManager em;
//
//    public JpaCategoryRepository(EntityManager em) {
//        this.em = em;
//    }
//
//
//    @Override
//    public Optional<Category> findByCategoryId(Long categoryId) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Category> findByCategoryName(String categoryName) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Category> findAll() {
//        return em.createQuery("select c from Category c", Category.class)
//                .getResultList();
//    }
//}
