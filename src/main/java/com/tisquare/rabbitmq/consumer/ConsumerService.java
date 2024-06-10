package com.tisquare.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class ConsumerService {

    private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @RabbitListener(queues = "${spring.rabbitmq.queue.name}", containerFactory = "rabbitListenerContainerFactory")
    public void receiveMessageFromQueue(LogMessage logMessage) {
        processMessage(logMessage);
    }

    private void processMessage(LogMessage logMessage) {
        try {
            logger.info(logMessage.toString());
        } catch (ListenerExecutionFailedException e) {
            logger.error("RabbitMQ Consumer | 수신 실패 | convert 오류: [{}]", e.getMessage());
        } catch (Exception e) {
            logger.error("RabbitMQ Consumer | 수신 실패 | 메시지: [{}] | 오류: [{}]", logMessage, e.getMessage());
        }
    }

}
