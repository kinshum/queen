package com.queen.rocketmq.controller;

import com.google.common.collect.Maps;
import com.queen.core.tool.jackson.JsonUtil;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 功能描述
 */
@RestController
@RequestMapping("/msg")
public class SendController {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @RequestMapping("/sendTest")
    public String sendTest() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {

        for (int i = 0; i < 500; i++) {
            Map<String,Object> data = Maps.newHashMap();
            data.put("name"+i,"kit"+i);
            Message message = new Message("rocketCommonTopic", "tag1", JsonUtil.toJson(data).getBytes());
            Message message1 = new Message("rocketCommonTopic", "tag2", JsonUtil.toJson(data).getBytes());
            SendResult send = defaultMQProducer.send(message);
            SendResult send1 = defaultMQProducer.send(message1);
            System.out.println("发送的消息："+ send.toString());
            System.out.println("1发送的消息："+ send1.toString());
        }
        return "success";
    }
}
