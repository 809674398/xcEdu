package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer02_subscribe_sms {

    //队列
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    private static final String EXCHANGE_FANOUT_INFORM="exchange_fanout_inform";

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
            connection=connectionFactory.newConnection();
            //创建回话通道
            Channel channel = connection.createChannel();
            //声明队列,如果队列在Mq中没有则要创建
            channel.queueDeclare(QUEUE_INFORM_SMS,true,false,false,null );
            //声明一个交换机
            channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
            //交换机和队列进行绑定
            channel.queueBind(QUEUE_INFORM_SMS,EXCHANGE_FANOUT_INFORM,"");
            //实现消费方法
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel){

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                   String message = new String(body,"utf-8");
                    System.out.println("receive message:"+message);
                }
            };


            //监听队列
            channel.basicConsume(QUEUE_INFORM_SMS,true,defaultConsumer);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
