package com.onlinejudge.javasandbox.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinejudge.javasandbox.models.Submission;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

@Component
public class Receiver {

    @RabbitListener(queues = "java-queue")
    public void consumeMessageFromQueue(String submissionInString) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Submission submission = objectMapper.readValue(submissionInString, Submission.class);
        System.out.println(submission);
    }

}
