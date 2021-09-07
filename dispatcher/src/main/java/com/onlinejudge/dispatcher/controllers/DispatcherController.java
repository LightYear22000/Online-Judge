package com.onlinejudge.dispatcher.controllers;

import com.onlinejudge.dispatcher.handlers.SubmissionHandler;
import com.onlinejudge.dispatcher.models.Submission;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api")
@PropertySource("classpath:config.properties")
public class DispatcherController {
    @Autowired
    private SubmissionHandler submissionHandler;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${dispatcher.rabbitmq.exchange.name}")
    private String exchange;

    @PostMapping("/submission")
    @ResponseBody
    public Submission postSubmission(@RequestBody Submission userSubmission) {
        System.out.println(userSubmission);
        return submissionHandler.createNewSubmission(userSubmission);
        // rabbitTemplate.convertAndSend(exchange, "", str);
        // return str;
    }

    @GetMapping("/submission")
    @ResponseBody
    Optional<Submission> getSubmission(@RequestParam Long sid) {
        return submissionHandler.getSubmissionById(sid);
    }
}
