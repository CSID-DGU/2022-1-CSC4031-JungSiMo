package com.example.usedtransactionservice.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "smartphone_detail_info")
@Entity
//@IdClass(DetailInfoPK.class)
public class SmartphoneDetailInfo {

    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "screen_size")
    private String screenSize;

    @Column(name = "telecom")
    private String telecom;

    @Column(name = "built_in_memory")
    private String builtInMemory;

    @Column(name = "os")
    private String os;

    @Column(name = "ram")
    private String ram;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "bluetooth")
    private String bluetooth;

    @Column(name = "front_camera_pixel")
    private String frontCameraPixel;

    @Column(name = "rear_camera_pixel")
    private String rearCameraPixel;

    @Column(name = "external_memory")
    private String externalMemory;

    @Column(name = "cable")
    private String cable;

    @Column(name = "width")
    private String width;

    @Column(name = "height")
    private String height;

    @Column(name = "thickness")
    private String thickness;

    @Column(name = "weight")
    private String weight;
}
