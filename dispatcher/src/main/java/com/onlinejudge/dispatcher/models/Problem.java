package com.onlinejudge.dispatcher.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String pid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int timeLimit;

    @Column(nullable = false)
    private short memoryLimit;
}
