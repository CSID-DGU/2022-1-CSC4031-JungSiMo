package com.example.usedtransactionservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ItemInfoPK implements Serializable {

    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Id
    @Column(name = "item_seq")
    private Long itemSeq;

//    private Long itemId;   // MapId("itemId) 로 매핑
//
//    @Column(name = "item_id")
//    private Long itemInfoId;

}
