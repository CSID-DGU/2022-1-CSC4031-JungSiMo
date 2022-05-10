package com.example.usedtransactionservice.UsedTransactionService.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="itemInfo")
public class ItemInfo implements Serializable {

    @Id
    @Column(name = "itemId")
    private long itemId;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemSeq")
    private long itemSeq;

    @Temporal(TemporalType.DATE)
    private Date itemDate;

    private long itemPrice;

    private String itemUrl;

    private String itemState;

    private String itemTransState;

}
