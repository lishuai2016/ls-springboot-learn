package com.ls.demo.queue.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/13 22:33
 */
public class SeckillEventFactory implements EventFactory<SeckillEvent> {

    public SeckillEvent newInstance() {
        return new SeckillEvent();
    }
}

