package com.onlinejudge.javasandbox.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class Submission {
    private Long sid;

    private String code;

    private Date timeStamp;

    private String language;

    private String verdict;

    private int timeTaken;

    private int memoryUsage;
}

