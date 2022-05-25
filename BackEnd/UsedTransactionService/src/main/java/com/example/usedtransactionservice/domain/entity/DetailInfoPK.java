package com.example.usedtransactionservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DetailInfoPK implements Serializable {

    @Id
    @Column(name = "item_id")
    private Long itemId;

//    @Id
//    @Column(name = "category_id")
//    private Long categoryId;

}
