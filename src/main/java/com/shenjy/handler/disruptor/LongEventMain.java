package com.shenjy.handler.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 【 enter the class description 】
 *
 * @author shenjy 2018/10/17
 */
public class LongEventMain {

    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        LongEventFactory factory = new LongEventFactory();

        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, 1024, executor);
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; true; i++) {
            byteBuffer.putInt(0, i);
            producer.onData(byteBuffer);
            Thread.sleep(1000);
        }
    }

}
