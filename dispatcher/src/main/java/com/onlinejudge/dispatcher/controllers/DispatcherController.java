package com.onlinejudge.dispatcher.controllers;

import com.onlinejudge.dispatcher.handlers.SubmissionHandler;
import com.onlinejudge.dispatcher.models.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class DispatcherController {
    @Autowired
    private SubmissionHandler submissionHandler;

    @GetMapping("/submission")
    @ResponseBody
    Submission getSubmission(@RequestParam Long sid) {
        return submissionHandler.getSubmissionById(sid);
    }
}
