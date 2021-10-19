package com.onlinejudge.javasandbox.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "pid")
    private Long pid;

    @Column(name = "input")
    private String input;

    @Column(name = "output")
    private String output;

}
