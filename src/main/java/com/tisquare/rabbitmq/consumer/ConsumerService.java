package com.tisquare.rabbitmq.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @RabbitListener(queues = "${spring.rabbitmq.queue.name}", containerFactory = "rabbitListenerContainerFactory")
    public void receiveMessageFromQueue(LogMessage logMessage, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey) {
        processMessage(logMessage, routingKey);
    }

    private void processMessage(LogMessage logMessage, String routingKey) {
        try {
            String[] routingKeyParts = routingKey.split("\\.");
            String taskName = routingKeyParts[1];
            String hostname = routingKeyParts[2];
            logMessage.setHostname(hostname);
            logMessage.setTaskname(taskName);

            logger.info("RabbitMQ Consumer | 수신 성공 | [{}]", logMessage);
        } catch (ListenerExecutionFailedException e) {
            logger.error("RabbitMQ Consumer | 수신 실패 | convert 오류: [{}]", e.getMessage());
        } catch (Exception e) {
            logger.error("RabbitMQ Consumer | 수신 실패 | 메시지: [{}] | 오류: [{}]", logMessage, e.getMessage());
        }
    }

}
