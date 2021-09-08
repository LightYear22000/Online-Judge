package com.onlinejudge.dispatcher.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinejudge.dispatcher.models.Submission;
import com.onlinejudge.dispatcher.repositories.SubmissionRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@PropertySource("classpath:config.properties")
public class SubmissionHandler {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Value("${dispatcher.rabbitmq.exchange.name}")
    private String exchange;

    public Optional<Submission> getSubmissionById(Long id) {
        return submissionRepository.findById(id);
    }
    public Submission createNewSubmission(Submission userSubmission) throws Exception {
        Submission submission =  submissionRepository.save(userSubmission);
        ObjectMapper objectMapper = new ObjectMapper();
        rabbitTemplate.convertAndSend(exchange, "", objectMapper.writeValueAsString(submission));
        return submission;
    }
}