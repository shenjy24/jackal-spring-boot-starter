package com.shenjy.handler.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/10/17
 */
public class MsgEventListener {

    @Subscribe
    public void listen(MsgEvent msgEvent) {
        System.out.println(msgEvent.getMsg());
    }
}
