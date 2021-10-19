package com.onlinejudge.javasandbox.models;

import lombok.Data;

@Data
public class Problem {
    private Long pid;

    private String name;

    private int timeLimit;

    private String input;

    private String output;

    private short memoryLimit;
}