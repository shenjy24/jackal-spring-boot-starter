package com.jonas.service.localevent;

import org.springframework.context.ApplicationEventPublisher;

/**
 * 【 本地事件 】
 *
 * @author shenjy 2019/04/30
 */
public class LocalEvent {
    private ApplicationEventPublisher publisher;
    private String topic;
    private Object content;

    public static LocalEvent builder(ApplicationEventPublisher publisher) {
        LocalEvent localEvent = new LocalEvent();
        localEvent.setPublisher(publisher);
        return localEvent;
    }

    public LocalEvent setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public LocalEvent setContent(Object content) {
        this.content = content;
        return this;
    }

    private void setPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish() {
        publisher.publishEvent(this);
    }

    public String getTopic() {
        return topic;
    }

    public Object getContent() {
        return content;
    }
}
