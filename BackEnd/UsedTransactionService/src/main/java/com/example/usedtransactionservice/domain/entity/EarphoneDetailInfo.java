package com.example.usedtransactionservice.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "earphone_detail_info")
@Entity
@IdClass(DetailInfoPK.class)
public class EarphoneDetailInfo {

//    @EmbeddedId
//    private DetailInfoPK detailInfoPK;

    @Id
    @Column(name = "item_id")
    private Long itemId;

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
