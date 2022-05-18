package com.example.usedtransactionservice.UsedTransactionService.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="user_stream")
public class UserStream {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stream_id")
    private Long streamId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "item_id")
    private Long itemId;

}
