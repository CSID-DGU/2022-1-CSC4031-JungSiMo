package com.example.usedtransactionservice.domain.repository;

import com.example.usedtransactionservice.domain.dto.responseParam.ItemPriceChangeResponse;
import com.example.usedtransactionservice.domain.dto.responseParam.ItemPriceChangeSummaryResponse;
import com.example.usedtransactionservice.domain.entity.ItemInfo;
import com.example.usedtransactionservice.domain.entity.QItemInfo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
public class ItemInfoRepositoryImpl implements ItemInfoCustomRepository {

    private ItemPriceChangeResponse itemPriceChangeResponse;

    private final JPAQueryFactory jpaQueryFactory;

    public ItemInfoRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


    public List<ItemInfo> findAll() {
        QItemInfo itemInfo = QItemInfo.itemInfo;
        return jpaQueryFactory.selectFrom(itemInfo)
                .fetch();
    }

    public List<ItemInfo> findByItemIdAndItemState(Long itemId, String itemState) {
        QItemInfo itemInfo = QItemInfo.itemInfo;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(itemInfo.itemId.eq(itemId));
        builder.and(itemInfo.itemState.eq(itemState));

        return jpaQueryFactory.selectFrom(itemInfo)
                .where(builder)
                .fetch();

    }

    // 일/주/월 단위 조회해야 할 기간의 시작 날짜 받아오기
    private BooleanExpression startAfter(String pricePeriod) {
        QItemInfo itemInfo = QItemInfo.itemInfo;
        LocalDate date = LocalDate.now();

        switch (pricePeriod) {
            case "1d": date = date.minusDays(5); break;
            case "1w": date = date.minusWeeks(5); break;
            case "1m": date = date.minusMonths(5); break;
            default: return null;
        }

        return itemInfo.itemDate.after(LocalDate.from(date));
    }

    public List<Tuple> findByItemDateBetween(Long itemId, LocalDate start, LocalDate end) {

        QItemInfo itemInfo = QItemInfo.itemInfo;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(itemInfo.itemId.eq(itemId));
        builder.and(itemInfo.itemDate.between(end, start));
//        builder.and(itemInfo.itemDate.goe(end));
//        builder.and(itemInfo.itemDate.loe(start));

        return jpaQueryFactory.from(itemInfo)
                .select(itemInfo.itemState
                        , itemInfo.itemPrice.avg().longValue())
                .where(builder)
                .groupBy(itemInfo.itemState)
                .orderBy(itemInfo.itemState.asc())
                .fetch();

    }

    public List<List<ItemPriceChangeResponse>> priceChangeInfo(Long itemId, String pricePeriod) {

        List<List<ItemPriceChangeResponse>> resultList = new ArrayList<>();

        LocalDate date = LocalDate.now();
        LocalDate start = date;   // 시작 날짜 초기화 - 오늘

        int one = 1;
        Long avg = Long.valueOf(0);

        if (pricePeriod.equals("1d")) {
            LocalDate end = date.minusDays(one);

            for (int i = 0; i <= 5; i++) {

                List<Tuple> tupleList = findByItemDateBetween(itemId, start, end);
                List<ItemPriceChangeResponse> tmpList = new ArrayList<>();

                for (Tuple t : tupleList) {
                    ItemPriceChangeResponse itemPriceChangeResponse = new ItemPriceChangeResponse();
                    itemPriceChangeResponse.setItemState(t.get(0, String.class));
                    itemPriceChangeResponse.setItemPrice(t.get(1, long.class));
                    itemPriceChangeResponse.setItemDate(start);
                    tmpList.add(itemPriceChangeResponse);
                }

                resultList.add(tmpList);

                start = end;
                end = end.minusDays(one);

            }

        } else if (pricePeriod.equals("1w")) {
            LocalDate end = date.minusWeeks(one);

            for (int i = 0; i <= 5; i++) {

                List<Tuple> tupleList = findByItemDateBetween(itemId, start, end);
                List<ItemPriceChangeResponse> tmpList = new ArrayList<>();

                for (Tuple t : tupleList) {
                    ItemPriceChangeResponse itemPriceChangeResponse = new ItemPriceChangeResponse();
                    itemPriceChangeResponse.setItemState(t.get(0, String.class));
                    itemPriceChangeResponse.setItemPrice(t.get(1, long.class));
                    itemPriceChangeResponse.setItemDate(start);
                    tmpList.add(itemPriceChangeResponse);
                }

                resultList.add(tmpList);

/*
                // list 에는 기간별 itemDate, itemState, itemPrice 가 담기도록
                // 한 주의 정보만 포함
                tupleList.stream().forEach(tuple -> {
                    System.out.println("tuple : " + tuple);   // [[중, 180000], [상, 189530], [하, 63333]]
                    System.out.println("itemState : " + tuple.get(0, String.class));
                    System.out.println("Average Price : " + tuple.get(1, long.class));
                    avgPrice[0] = Long.valueOf(tuple.get(1, long.class));
                    average[0] = Long.valueOf(tuple.get(1, long.class));

 */

                start = end;
                end = end.minusWeeks(one);

            }


        } else if (pricePeriod.equals("1m")) {
            LocalDate end = date.minusMonths(one);

            for (int i = 0; i <= 5; i++) {

                List<Tuple> tupleList = findByItemDateBetween(itemId, start, end);
                List<ItemPriceChangeResponse> tmpList = new ArrayList<>();

                for (Tuple t : tupleList) {
                    ItemPriceChangeResponse itemPriceChangeResponse = new ItemPriceChangeResponse();
                    itemPriceChangeResponse.setItemState(t.get(0, String.class));
                    itemPriceChangeResponse.setItemPrice(t.get(1, long.class));
                    itemPriceChangeResponse.setItemDate(start);
                    tmpList.add(itemPriceChangeResponse);
                }

                resultList.add(tmpList);

                start = end;
                end = end.minusMonths(one);

            }
        }

        return resultList;

    }

    // TODO 최솟값 요약 정보
   public List<Tuple> findByItemDateBetweenAgg(Long itemId, LocalDate start, LocalDate end) {

        QItemInfo itemInfo = QItemInfo.itemInfo;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(itemInfo.itemId.eq(itemId));
        builder.and(itemInfo.itemDate.between(end, start));

        return jpaQueryFactory.from(itemInfo)
                .select(itemInfo.itemState
                        , itemInfo.itemPrice.min().longValue()
                        , itemInfo.itemPrice.avg().longValue()
                        , itemInfo.itemPrice.max().longValue()
                        , itemInfo.itemSource
                        , itemInfo.itemUrl
                )
                .where(builder)
                .groupBy(itemInfo.itemState)
                .orderBy(itemInfo.itemState.asc())
                .fetch();

    }

    // TODO 최저가 요약 정보
    public List<Tuple> findByItemDateBetweenMin(Long itemId, LocalDate start, LocalDate end) {

        QItemInfo itemInfo = QItemInfo.itemInfo;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(itemInfo.itemId.eq(itemId));
        builder.and(itemInfo.itemDate.between(end, start));
        return jpaQueryFactory.from(itemInfo)
                .select(itemInfo.itemState,
                        itemInfo.itemPrice.eq(
                                JPAExpressions.select(itemInfo.itemPrice.min())
                                .from(itemInfo)
                        ),
                        itemInfo.itemSource,
                        itemInfo.itemSource
                )
                .fetch();

    }

    // TODO 최고가 요약 정보
//    public List<Tuple> indByItemDateBetweenMax(Long itemId, LocalDate start, LocalDate end) {
//        QItemInfo itemInfo = QItemInfo.itemInfo;
//
//        BooleanBuilder builder = new BooleanBuilder();
//        builder.and(itemInfo.itemId.eq(itemId));
//        builder.and(itemInfo.itemDate.between(end, start));
//        return jpaQueryFactory.from(itemInfo)
//                .select()
//    }



    public List<List<ItemPriceChangeSummaryResponse>> priceChangeSummaryInfo(Long itemId, String pricePeriod) {
        List<List<ItemPriceChangeSummaryResponse>> resultList = new ArrayList<>();
        // 각 기간별 상품 상태에 따른 가격 - 최댓값, 평균값 , 최솟값 - 사이트 - url

        LocalDate date = LocalDate.now();
        LocalDate start = date;

        int one = 1;
        Long avg = Long.valueOf(0);

        if (pricePeriod.equals("1d")) {
            LocalDate end = date.minusDays(one);

            for (int i = 0; i <= 5; i++) {

                List<Tuple> tupleList = findByItemDateBetweenAgg(itemId, start, end);
                List<ItemPriceChangeSummaryResponse> tmpList = new ArrayList<>();

                for (Tuple t : tupleList) {
                    System.out.println("t : " + t);
                    ItemPriceChangeSummaryResponse itemPriceChangeSummaryResponse = new ItemPriceChangeSummaryResponse();
                    itemPriceChangeSummaryResponse.setItemState(t.get(0, String.class));
                    itemPriceChangeSummaryResponse.setItemMinPrice(t.get(1, long.class));
                    itemPriceChangeSummaryResponse.setItemAvgPrice(t.get(2, long.class));
                    itemPriceChangeSummaryResponse.setItemMaxPrice(t.get(3, long.class));
                    itemPriceChangeSummaryResponse.setItemSource(t.get(4, String.class));
                    itemPriceChangeSummaryResponse.setItemUrl(t.get(5, String.class));
                    itemPriceChangeSummaryResponse.setItemDate(start);

                    tmpList.add(itemPriceChangeSummaryResponse);
                }

                resultList.add(tmpList);

                start = end;
                end = end.minusDays(one);

            }

        } else if (pricePeriod.equals("1w")) {
            LocalDate end = date.minusWeeks(one);

            for (int i = 0; i <= 5; i++) {

                List<Tuple> tupleList = findByItemDateBetweenAgg(itemId, start, end);
                List<ItemPriceChangeSummaryResponse> tmpList = new ArrayList<>();

                for (Tuple t : tupleList) {
                    ItemPriceChangeSummaryResponse itemPriceChangeSummaryResponse = new ItemPriceChangeSummaryResponse();
                    itemPriceChangeSummaryResponse.setItemState(t.get(0, String.class));
                    itemPriceChangeSummaryResponse.setItemMinPrice(t.get(1, long.class));
                    itemPriceChangeSummaryResponse.setItemAvgPrice(t.get(2, long.class));
                    itemPriceChangeSummaryResponse.setItemMaxPrice(t.get(3, long.class));
                    itemPriceChangeSummaryResponse.setItemSource(t.get(4, String.class));
                    itemPriceChangeSummaryResponse.setItemUrl(t.get(5, String.class));
                    itemPriceChangeSummaryResponse.setItemDate(start);

                    tmpList.add(itemPriceChangeSummaryResponse);
                }

                resultList.add(tmpList);


                start = end;
                end = end.minusWeeks(one);

            }


        } else if (pricePeriod.equals("1m")) {
            LocalDate end = date.minusMonths(one);

            for (int i = 0; i <= 5; i++) {

                List<Tuple> tupleList = findByItemDateBetweenAgg(itemId, start, end);
                List<ItemPriceChangeSummaryResponse> tmpList = new ArrayList<>();

                for (Tuple t : tupleList) {
                    ItemPriceChangeSummaryResponse itemPriceChangeSummaryResponse = new ItemPriceChangeSummaryResponse();
                    itemPriceChangeSummaryResponse.setItemState(t.get(0, String.class));
                    itemPriceChangeSummaryResponse.setItemMinPrice(t.get(1, long.class));
                    itemPriceChangeSummaryResponse.setItemAvgPrice(t.get(2, long.class));
                    itemPriceChangeSummaryResponse.setItemMaxPrice(t.get(3, long.class));
                    itemPriceChangeSummaryResponse.setItemSource(t.get(4, String.class));
                    itemPriceChangeSummaryResponse.setItemUrl(t.get(5, String.class));
                    itemPriceChangeSummaryResponse.setItemDate(start);

                    tmpList.add(itemPriceChangeSummaryResponse);
                }

                resultList.add(tmpList);

                start = end;
                end = end.minusMonths(one);

            }
        }

        return resultList;
    }
}
