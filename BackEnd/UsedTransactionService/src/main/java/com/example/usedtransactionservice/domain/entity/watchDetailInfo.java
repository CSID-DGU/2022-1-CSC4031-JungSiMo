package com.example.usedtransactionservice.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="watch_detail_info")
@Entity
public class watchDetailInfo implements Serializable {

    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Id
    @Column(name = "category_d")
    private Long categoryId;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "compatible_os")
    private String compatibleOs;

    @Column(name = "network")
    private String network;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "built_in_os")
    private String builtInOs;

    @Column(name = "band_shape")
    private String bandShape;

    @Column(name = "light_sensor")
    private String lightSensor;

    @Column(name = "screen_type")
    private String screenType;

    @Column(name = "built_in_memory")
    private String builtInMemory;

    @Column(name = "ram")
    private String ram;

    @Column(name = "usage_time")
    private String usageTime;

    @Column(name = "charging")
    private String charging;

}
