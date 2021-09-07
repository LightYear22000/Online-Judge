package com.onlinejudge.dispatcher.handlers;

import com.onlinejudge.dispatcher.models.Submission;
import com.onlinejudge.dispatcher.repositories.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SubmissionHandler {

    @Autowired
    private SubmissionRepository submissionRepository;

    public Optional<Submission> getSubmissionById(Long id) {
        return submissionRepository.findById(id);
    }
    public Submission createNewSubmission(Submission userSubmission) {
        return submissionRepository.save(userSubmission);
    }
}