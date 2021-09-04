package com.onlinejudge.dispatcher.handlers;

import com.onlinejudge.dispatcher.models.Submission;
import org.springframework.stereotype.Component;

@Component
public class SubmissionHandler {

    public Submission getSubmissionById(Long id) {
        Submission submission = new Submission();
        for(int i = 0; i <  1000000; i++) {
            i++;
            id++;
        }
        return submission;
    }
}
