package com.onlinejudge.dispatcher.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
public class Receiver {

    @RabbitListener(queues = "${dispatcher.rabbitmq.queue.name}")
    public void consumeMessageFromQueue(Long str) {
        System.out.print(str + " Inside consumeMessageFromQueue");
    }

}
