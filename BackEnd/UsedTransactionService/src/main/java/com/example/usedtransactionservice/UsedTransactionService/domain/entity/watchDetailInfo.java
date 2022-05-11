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
@Table(name="watchDetailInfo")
public class watchDetailInfo implements Serializable {

    @Id
    @Column(name = "itemId")
    private long itemId;

    @Id
    @Column(name = "categoryId")
    private long categoryId;

    private String itemImage;

    private String compatibleOs;

    private String network;

    private String resolution;

    private String builtInOs;

    private String bandShape;

    private String lightSensor;

    private String screenType;

    private String builtInMemory;

    private String ram;

    private String usageTime;

    private String charging;

}
