package com.ls.demo.queue.disruptor;

import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/13 22:34
 */
public class SeckillEventProducer {

    private final static EventTranslatorVararg<SeckillEvent> translator = new EventTranslatorVararg<SeckillEvent>() {
        public void translateTo(SeckillEvent seckillEvent, long seq, Object... objs) {
            seckillEvent.setSeckillId((Long) objs[0]);
            seckillEvent.setUserId((Long) objs[1]);
        }
    };

    private final RingBuffer<SeckillEvent> ringBuffer;

    public SeckillEventProducer(RingBuffer<SeckillEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void seckill(long seckillId, long userId){
        System.out.println("生产者生产的秒杀id和用户id："+seckillId+","+userId);
        this.ringBuffer.publishEvent(translator, seckillId, userId);
    }
}
