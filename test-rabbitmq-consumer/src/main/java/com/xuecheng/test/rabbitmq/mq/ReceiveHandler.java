package com.xuecheng.test.rabbitmq.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.xuecheng.test.rabbitmq.config.RabbitmqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveHandler {

    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})
    public void receive_msg(String msg, Message message, Channel channel){
        System.out.println(msg);
    }
}
