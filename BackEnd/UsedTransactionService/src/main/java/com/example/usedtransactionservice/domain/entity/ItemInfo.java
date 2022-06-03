package com.example.usedtransactionservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_info")
@Entity
@IdClass(ItemInfoPK.class)
public class ItemInfo {

    @Id
    @Column(name = "item_id")
    private Long itemId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_seq")
    private Long itemSeq;

    @Column(name = "item_date")
//    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private LocalDate itemDate;

//    @Column(name = "item_date")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDateTime itemDate;

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
