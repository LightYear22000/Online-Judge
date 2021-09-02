package com.onlinejudge.dispatcher.models;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Date timeStamp;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String verdict;

    @Column(nullable = false)
    private int timeTaken;

    @Column(nullable = false)
    private int memoryUsage;
}
