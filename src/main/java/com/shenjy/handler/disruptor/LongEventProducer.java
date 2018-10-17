package com.shenjy.handler.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;
import java.util.Random;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/10/17
 */
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * 发布事件最少需要两步：获取下一个事件槽并发布事件（发布事件的时候要使用try/finally保证事件一定会被发布）。
     * 如果我们使用RingBuffer.next()获取一个事件槽，那么一定要发布对应的事件。
     * 如果不能发布事件，那么就会引起Disruptor状态的混乱。
     * 尤其是在多个事件生产者的情况下会导致事件消费者失速，从而不得不重启应用才能会恢复。
     */
    public void onData(ByteBuffer byteBuffer) {
        long sequence = ringBuffer.next();
        try {
            LongEvent event = ringBuffer.get(sequence);
            event.setValue(byteBuffer.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
