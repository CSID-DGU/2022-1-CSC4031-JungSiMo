package com.example.usedtransactionservice.UsedTransactionService.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter @Setter
@Entity
@Table(name="user")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    private String userName;

    private String userPassword;
}
