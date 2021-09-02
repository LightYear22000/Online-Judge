package com.onlinejudge.dispatcher.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long uid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String handle;

    @Column(nullable = false)
    private String password;
}
