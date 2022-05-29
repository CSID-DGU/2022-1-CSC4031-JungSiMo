package com.example.usedtransactionservice.domain.repository;

import com.example.usedtransactionservice.domain.dto.responseParam.ItemPriceChangeResponse;
import com.example.usedtransactionservice.domain.dto.responseParam.ItemPriceChangeResponseInterface;
import com.example.usedtransactionservice.domain.entity.ItemInfo;
import com.example.usedtransactionservice.domain.entity.QItemInfo;
import com.example.usedtransactionservice.domain.entity.TimeBetween;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@Repository
public class ItemInfoRepositoryImpl implements ItemInfoCustomRepository {

    private ItemPriceChangeResponse itemPriceChangeResponse;
    private ItemPriceChangeResponseInterface itemPriceChangeResponseInterface;

    private final JPAQueryFactory jpaQueryFactory;
    private TimeBetween timeBetween;

    public ItemInfoRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    QItemInfo itemInfo = QItemInfo.itemInfo;

    public List<ItemInfo> findAll() {
        return jpaQueryFactory.selectFrom(itemInfo)
                .fetch();
    }

    public List<ItemInfo> findByItemIdAndItemState(Long itemId, String itemState) {
        return jpaQueryFactory.selectFrom(itemInfo)
                .where(itemInfo.itemId.eq(itemId)
                    .and(itemInfo.itemState.eq(itemState)))
                .fetch();

    }

    // 일/주/월 단위 조회해야 할 기간의 시작 날짜 받아오기
    private BooleanExpression startAfter(String pricePeriod) {
        LocalDate date = LocalDate.now();
//        LocalDateTime localDate = LocalDateTime.now();

        switch (pricePeriod) {
            case "1d": date = date.minusDays(5); break;
            case "1w": date = date.minusWeeks(5); break;
            case "1m": date = date.minusMonths(5); break;
            default: return null;
        }
//        return itemInfo.itemDate.after(localDate);
        return itemInfo.itemDate.after(Expressions.dateTemplate(Date.class, "{0}", date));
    }

    //
    private BooleanExpression startBetween(String pricePeriod) {
        LocalDate date = LocalDate.now();

        switch (pricePeriod) {
            case "1d" :
        }

        return itemInfo.itemDate.after(Expressions.dateTemplate(Date.class, "{0}", date));
    }

    @Override
    public List<Tuple> findByItemDateBetween2(Long itemId, LocalDate start, LocalDate end) {
        List<ItemInfo> list = new ArrayList<>();

        List<Tuple> itemInfoList = jpaQueryFactory.select(itemInfo.itemDate, itemInfo.itemDate)
                .from(itemInfo)
                .where(itemInfo.itemId.eq(itemId)
                        .and(itemInfo.itemDate.between(Expressions.dateTemplate(Date.class, "{0}", start), Expressions.dateTemplate(Date.class, "{0}", end))))
//                .groupBy(itemInfo.itemState)
                .fetch();

        return itemInfoList;
    }

    public List<ItemInfo> price(Long itemId, String pricePeriod) {
        System.out.println(pricePeriod);
        List<ItemInfo> list = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Long tmpSum = Long.valueOf(0);
        int cnt;

        // 시작 날짜 초기화 - 오늘
        LocalDate start = date;
        log.info("itemDate start : " + start);
        log.info("itemDate end : " + date.minusDays(1));
        log.info("itemDate end : " + date.minusWeeks(1));
        log.info("itemDate end : " + date.minusMonths(1));
        List<Tuple> tmpList = findByItemDateBetween2(itemId, date, date.minusMonths(1));
        log.info(tmpList);
        // 끝 날짜 초기화 - 1주 전
        int day = 1;
        Long avg = Long.valueOf(0);
        if (pricePeriod.equals("1d")) {
            LocalDate end = date.minusDays(1);
            log.info("itemDate end : " + end);

            for (int i = 0; i < 5; i++) {
                List<Tuple> tupleList = findByItemDateBetween2(itemId, date, date.minusDays(1));
                cnt = tupleList.size();

                for (Tuple t : tupleList) {
                    tmpSum += t.get(itemInfo.itemPrice);
                    log.info("itemDate" + itemInfo.itemDate + " itemPrice : " + itemInfo.itemPrice);
                }
                if (cnt != 0) {
                    avg = tmpSum / cnt;
                }

                System.out.println("start : " + start + "end : " + end + " 일 때 가격 : " + avg);
                log.info("start : " + start + " end : " + end + " 일 때 가격 : " + avg);

                start = date.minusDays(day);   // 0 -> 1 -> 2 -> 3 -> 4
                day += 1;
                end = date.minusDays(day);   // 1-> 2 -> 3 -> 4 -> 5

                list.add(ItemInfo.builder()
                        .itemDate(start)
                        .itemPrice(avg)
                        .build());
            }

        } else if (pricePeriod.equals("1w")) {
            LocalDate end = date.minusWeeks(1);
            log.info("itemDate end : " + end);
            for (int i = 0; i < 5; i++) {
                List<Tuple> tupleList = findByItemDateBetween2(itemId, date, date.minusWeeks(1));
                log.info(tupleList);
                cnt = tupleList.size();

                for (Tuple t : tupleList) {
                    tmpSum += t.get(itemInfo.itemPrice);
                    log.info("itemDate" + itemInfo.itemDate + " itemPrice : " + itemInfo.itemPrice);
                }
                if (cnt != 0) {
                    avg = tmpSum / cnt;
                }

                System.out.println("start : " + start + "end : " + end + " 일 때 가격 : " + avg);
                log.info("start : " + start + " end : " + end + " 일 때 가격 : " + avg);

                start = date.minusWeeks(day);
                day += 1;
                end = date.minusWeeks(day);

                list.add(ItemInfo.builder()
                        .itemDate(start)
                        .itemPrice(avg)
                        .build());

            }

        } else if (pricePeriod.equals("1m")) {
            LocalDate end = date.minusMonths(1);
            log.info("itemDate end : " + end);
            for (int i = 0; i < 5; i++) {
                List<Tuple> tupleList = findByItemDateBetween2(itemId, date, date.minusMonths(1));
                cnt = tupleList.size();

                for (Tuple t : tupleList) {
                    tmpSum += t.get(itemInfo.itemPrice);
                    log.info("itemDate" + itemInfo.itemDate + " itemPrice : " + itemInfo.itemPrice);
                }
                if (cnt != 0) {
                    avg = tmpSum / cnt;
                }

                System.out.println("start : " + start + " end : " + end + " 일 때 가격 : " + avg);
                log.info("start : " + start + "end : " + end + " 일 때 가격 : " + avg);

                start = date.minusMonths(day);   // 0 -> 1 -> 2 -> 3 -> 4
                day += 1;
                end = date.minusMonths(day);   // 1-> 2 -> 3 -> 4 -> 5

                list.add(ItemInfo.builder()
                        .itemDate(start)
                        .itemPrice(avg)
                        .build());

            }

        }

        return list;

    }


    @Override
    public List<ItemInfo> findByItemDateBetween(LocalDate start, LocalDate end) {
        return null;
    }
}
