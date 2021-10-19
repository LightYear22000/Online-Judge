package com.onlinejudge.javasandbox.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinejudge.javasandbox.JavaWorker.Worker;
import com.onlinejudge.javasandbox.models.Submission;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

import java.nio.file.Path;

@Component
@PropertySource("classpath:config.properties")
public class Receiver {
    @Autowired
    private Worker worker;

    @Value("${jworker.filepath.java}")
    private String PathName;

    @RabbitListener(queues = "java-queue")
    public void consumeMessageFromQueue(String submissionInString) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Submission submission = objectMapper.readValue(submissionInString, Submission.class);
        System.out.println(submission);
        worker.save(PathName, submission);
        worker.compile(PathName, submission);
    }

}
