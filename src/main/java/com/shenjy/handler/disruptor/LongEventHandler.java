package com.shenjy.handler.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/10/17
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println(longEvent.getValue());
        System.out.println(l);
        System.out.println(b);
    }
}
