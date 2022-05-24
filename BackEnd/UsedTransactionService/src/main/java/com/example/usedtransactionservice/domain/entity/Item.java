package com.example.usedtransactionservice.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_brand")
    private String itemBrand;

}
