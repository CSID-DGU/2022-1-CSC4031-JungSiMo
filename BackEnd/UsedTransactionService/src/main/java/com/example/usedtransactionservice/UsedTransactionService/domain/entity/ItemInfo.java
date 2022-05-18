package com.example.usedtransactionservice.UsedTransactionService.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="item_info")
public class ItemInfo implements Serializable {

    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_seq")
    private Long itemSeq;

    @Temporal(TemporalType.DATE)
    private Date itemDate;

    private Long itemPrice;

    private String itemUrl;

    private String itemState;

    private String itemTransState;

    private String itemSource;

}
