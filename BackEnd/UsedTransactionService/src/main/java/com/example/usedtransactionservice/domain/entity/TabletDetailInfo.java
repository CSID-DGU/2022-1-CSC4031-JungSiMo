package com.example.usedtransactionservice.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tablet_detail_info")
@Entity
//@IdClass(DetailInfoPK.class)
public class TabletDetailInfo {

    private Long itemId;

    private Long categoryId;

    private String itemImage;

    private String shape;

    private String internetConnection;

    private String releaseOs;

    private String cpuCore;

    private String screenSize;

    private String panel;

    private String builtInMemory;

    private String externalMemory;

    private String audio;

    private String frontCameraPixel;

    private String rearCameraPixel;

    private String blueTooth;

    private String pen;

    private String weight;

    private String thickness;

    private String resolution;

    private String width;

    private String height;

}
