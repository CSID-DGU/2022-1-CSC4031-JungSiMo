package com.example.usedtransactionservice.UsedTransactionService.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="item")
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
