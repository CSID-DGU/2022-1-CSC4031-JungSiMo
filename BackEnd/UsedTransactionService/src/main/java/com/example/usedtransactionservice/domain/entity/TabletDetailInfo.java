package com.example.usedtransactionservice.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tablet_detail_info")
@Entity
@IdClass(DetailInfoPK.class)
public class TabletDetailInfo {


    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "shape")
    private String shape;

    @Column(name = "internet_connection")
    private String internetConnection;

    @Column(name = "release_os")
    private String releaseOs;

    @Column(name = "cpu_core")
    private String cpuCore;

    @Column(name = "screen_size")
    private String screenSize;

    @Column(name = "panel")
    private String panel;

    @Column(name = "built_in_memory")
    private String builtInMemory;

    @Column(name = "external_memory")
    private String externalMemory;

    @Column(name = "audio")
    private String audio;

    @Column(name = "front_camera_pixel")
    private String frontCameraPixel;

    @Column(name = "rear_camera_pixel")
    private String rearCameraPixel;

    @Column(name = "bluetooth")
    private String bluetooth;

    @Column(name = "pen")
    private String pen;

    @Column(name = "weight")
    private String weight;

    @Column(name = "thickness")
    private String thickness;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "width")
    private String width;

    @Column(name = "height")
    private String height;

}
