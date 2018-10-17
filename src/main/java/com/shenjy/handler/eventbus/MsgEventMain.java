package com.shenjy.handler.eventbus;

import com.google.common.eventbus.EventBus;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/10/17
 */
public class MsgEventMain {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        MsgEventListener listener = new MsgEventListener();
        eventBus.register(listener);

        eventBus.post(new MsgEvent("hello"));

    }
}
