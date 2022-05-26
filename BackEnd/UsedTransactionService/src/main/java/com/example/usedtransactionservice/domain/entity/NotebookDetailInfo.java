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
//@IdClass(DetailInfoPK.class)
public class NotebookDetailInfo {

    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "item_image")
    private String itemImage;
}
