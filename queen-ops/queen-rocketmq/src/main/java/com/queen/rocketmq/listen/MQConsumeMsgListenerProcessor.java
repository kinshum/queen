package com.queen.rocketmq.listen;

import com.queen.core.tool.jackson.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class MQConsumeMsgListenerProcessor implements MessageListenerConcurrently {


    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        log.info("list:{}", list.size());
        if (CollectionUtils.isEmpty(list)) {
            log.info("消息为空，不做处理");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        String topic = messageExt.getTopic();
        String tags = messageExt.getTags();
        log.info("topic名称--{} tag名称=={}", topic, tags);
        if ("tag1".equals(tags)) {
            String msg = new String(messageExt.getBody());
            Map<String, Object> parse = JsonUtil.parse(msg, Map.class);
            for (Map.Entry<String, Object> mm : parse.entrySet()) {
                log.info("tag1--接收到的消息===key:{}---val:{}" + mm.getKey(), mm.getValue());
            }
        } else if ("tag2".equals(tags)) {
            String msg = new String(messageExt.getBody());
            Map<String, Object> parse = JsonUtil.parse(msg, Map.class);
            for (Map.Entry<String, Object> mm : parse.entrySet()) {
                log.info("tag2--接收到的消息===key:{}" + mm.getKey());
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
