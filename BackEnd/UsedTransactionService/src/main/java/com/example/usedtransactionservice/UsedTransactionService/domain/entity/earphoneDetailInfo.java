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
@Table(name="earphone_detail_info")
public class earphoneDetailInfo implements Serializable {

    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "bluetooth_version")
    private String bluetoothVersion;

    @Column(name = "shape")
    private String shape;

    @Column(name = "channel")
    private String channel;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "sound_quality_first")
    private String soundQualityFirst;

    @Column(name = "sound_quality_second")
    private String soundQualitySecond;

    @Column(name = "operation_func")
    private String operationFunc;

    @Column(name = "waterproof")
    private String waterproof;

    @Column(name = "microphone_yn")
    private String microphoneYn;

    @Column(name ="usage_time")
    private String usageTime;

    @Column(name = "terminal")
    private String terminal;

}
