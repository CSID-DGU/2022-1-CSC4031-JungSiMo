package com.example.usedtransactionservice.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_stream")
@Entity
public class UserStream {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stream_id")
    private Long streamId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "item_id")
    private Long itemId;

}
