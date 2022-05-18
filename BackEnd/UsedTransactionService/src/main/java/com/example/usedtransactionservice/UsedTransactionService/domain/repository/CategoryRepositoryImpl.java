package com.example.usedtransactionservice.UsedTransactionService.domain.repository;

import com.example.usedtransactionservice.UsedTransactionService.domain.dto.responseParam.CategorySearchResponse;
import com.example.usedtransactionservice.UsedTransactionService.domain.entity.Category;
import com.example.usedtransactionservice.UsedTransactionService.domain.entity.QCategory;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.*;

//@RequiredArgsConstructor
public class CategoryRepositoryImpl extends QuerydslRepositorySupport implements CategoryRepositoryForQueryDsl {
//
//    @Autowired
    private final JPAQueryFactory queryFactory;

    private QCategory category;

    private CategoryRepository categoryRepository;

    public CategoryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Category.class);
        this.queryFactory = queryFactory;
    }

    private static Map<Long, Category> store = new HashMap<>();

    // TODO 카테고리 이름 전체 조회

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public QueryResults<String> findAllCategory() {
        category = QCategory.category;
        QueryResults<String> categoryList = queryFactory
                .select(category.categoryName)
                .from(category)
                .fetchResults();
        return categoryList;
    }

    @Override
    public List<String> findAllCategory2() {

        category = QCategory.category;
        JPQLQuery<Tuple> query = from(category)
                .select(category.categoryName
                        , category.categoryId);
        List<Tuple> list = query.fetch();
        List<String> categoryList = new ArrayList<>();

        for (Tuple q : list) {
            categoryList.add(String.valueOf(q));
        }

        return categoryList;
    }

    @Override
    public List<CategorySearchResponse> findAllCategory3() {

        category = QCategory.category;
        JPQLQuery<CategorySearchResponse> query = queryFactory.select(Projections.fields(
                CategorySearchResponse.class
                , category.categoryName
                )).from(category);

        List<CategorySearchResponse> categoryList = query.fetch();

        return categoryList;
    }

    // TODO 카테고리 ID -> 카테고리 이름 조회
 /*
    @Override
    public String findByCategoryId(long catId) {
        category = QCategory.category;
        String categoryName = queryFactory
                .select(category.categoryName)
                .from(category)
                .where(category.categoryId.eq(catId))
                .fetchOne();
        return categoryName;
    }
  */
    @Override
    public Optional<Category> findByCategoryId(Long catId) {
        return Optional.ofNullable(store.get(catId));
    }

    // TODO 카테고리 이름 -> 카테고리 ID 조회(카테고리 이름 선택 후 호출)
/*
    @Override
    public Long findByCategoryName(String catName) {
        category = QCategory.category;
        Long categoryId = queryFactory
                .select(category.categoryId)
                .from(category)
                .where(category.categoryName.eq(catName))
                .fetchOne();
        return categoryId;
    }
 */
    @Override
    public Optional<Category> findByCategoryName(String catName) {
        return store.values().stream()   // 루프를 돌리며 -> member.getName()이 파라미터로 전달된 name과 같은지 확인 -> 같은 경우에만 필터링, 찾으면 반환
                .filter(category -> category.getCategoryName().equals(catName))
                .findAny();
    }



}
