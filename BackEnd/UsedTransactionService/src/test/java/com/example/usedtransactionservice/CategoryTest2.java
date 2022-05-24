package com.example.usedtransactionservice;

import com.example.usedtransactionservice.domain.entity.Category;
import com.example.usedtransactionservice.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class CategoryTest2 {
    @Autowired
    EntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void search() {

        Category category = new Category();
        entityManager.persist(category);

        System.out.println(categoryRepository.findAll());
    }
}