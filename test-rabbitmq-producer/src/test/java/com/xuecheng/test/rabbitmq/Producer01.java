package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer01 {

    //队列
    private static final String QUEUE="helloworld";

    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //设置虚拟机
        connectionFactory.setVirtualHost("/");
        //建立新连接
        Connection connection = null;
        try {
            connection=  connectionFactory.newConnection();
            //创建通道,生产者和消费者的通信都在通道中进行
            Channel channel = connection.createChannel();
            //声明队列,如果队列在Mq中没有则要创建
            channel.queueDeclare(QUEUE,true,false,false,null );
            //发送消息
            String message="helloworld 黑马";
            channel.basicPublish("",QUEUE,null,message.getBytes());
            System.out.println("send message:"+message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
