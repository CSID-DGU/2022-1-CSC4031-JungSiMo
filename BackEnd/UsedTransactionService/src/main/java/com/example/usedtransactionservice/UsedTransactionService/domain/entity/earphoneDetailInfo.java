package com.example.usedtransactionservice.UsedTransactionService.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="earphoneDetailInfo")
public class earphoneDetailInfo implements Serializable {

    @Id
    @Column(name = "itemId")
    private long itemId;

    @Id
    @Column(name = "categoryId")
    private long categoryId;

    private String itemImage;

    private String bluetoothVersion;

    private String shape;

    private String channel;

    private String purpose;

    private String soundQualityFirst;

    private String soundQualitySecond;

    private String operationFunc;

    private String waterproof;

    private String microphoneYnl;

    private String usageTime;

    private String terminal;

}
