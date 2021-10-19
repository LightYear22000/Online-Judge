package com.onlinejudge.javasandbox.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class Submission {

    public final static String STATUS_QUEUE = "IN QUEUE";
    public final static String STATUS_AC = "ACCEPTED";
    public final static String STATUS_CE = "COMPILATION ERROR";
    public final static String STATUS_RE = "RUNTIME ERROR";
    public final static String STATUS_TLE = "TIME LIMIT EXCEEDED";
    public final static String STATUS_MLE = "MEMORY LIMIT EXCEEDED";
    public final static String STATUS_WA = "WRONG ANSWER";

    private Long sid;

    private Long pid;

    private String code;

    private Date timeStamp;

    private String language;

    private String verdict;

    private int timeTaken;

    private int memoryUsage;
}

