package com.example.usedtransactionservice.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notebook_detail_info")
@Entity
public class NotebookDetailInfo {

    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "screen_size")
    private String screenSize;

    @Column(name = "weight")
    private String weight;

    @Column(name = "width")
    private String width;

    @Column(name = "height")
    private String height;

    @Column(name = "os")
    private String os;

    @Column(name = "core")
    private String core;

    @Column(name = "ram")
    private String ram;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "panel")
    private String panel;

    @Column(name = "cable")
    private String cable;

    @Column(name = "bluetooth")
    private String bluetooth;

    @Column(name = "built_in_memory")
    private String builtInMemory;

}
