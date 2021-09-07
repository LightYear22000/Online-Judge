package com.onlinejudge.dispatcher.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private EndUser endUser;

    @Column(nullable = false)
    private String code;

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
