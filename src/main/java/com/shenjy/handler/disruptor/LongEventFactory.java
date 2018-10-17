package com.shenjy.handler.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/10/17
 */
public class LongEventFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
