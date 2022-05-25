package com.example.usedtransactionservice.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_info")
@Entity
@IdClass(ItemInfoPK.class)
public class ItemInfo {

//    @EmbeddedId
//    private ItemInfoPK itemInfoId;
//
//    @MapsId("itemId")
//    @ManyToOne
////    @JoinColumn(name = "item_id")
//    public Item itemId;

    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_seq")
    private Long itemSeq;

    @Column(name = "item_date")
    @Temporal(TemporalType.DATE)
    private Date itemDate;

    @Column(name = "item_title")
    private String itemTitle;

    @Column(name = "item_price")
    private Long itemPrice;

    @Column(name = "item_url")
    private String itemUrl;

    @Column(name = "item_state")
    private String itemState;

    @Column(name = "item_trans_state")
    private String itemTransState;

    @Column(name = "item_source")
    private String itemSource;

}
