package com.onlinejudge.dispatcher.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int timeLimit;

    @Column(nullable = false)
    private String input;

    @Column(nullable = false)
    private String output;

    @Column(nullable = false)
    private short memoryLimit;
}
