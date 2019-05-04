package com.jonas.service.localevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 【 本地事件发送者 】
 *
 * @author shenjy 2019/05/04
 */
@Component
public class LocalEventSender {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publish(String topic, LocalMsg msg) {
        LocalEvent.builder(publisher)
                .setTopic(topic)
                .setContent(msg)
                .publish();
    }
}
