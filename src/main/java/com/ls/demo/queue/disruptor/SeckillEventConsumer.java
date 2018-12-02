package com.ls.demo.queue.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/13 22:34
 */
public class SeckillEventConsumer implements EventHandler<SeckillEvent> {
    public void onEvent(SeckillEvent seckillEvent, long seq, boolean bool) throws Exception {
        //保存秒杀的结果到数据库
        System.out.println("消费者消费秒杀id和用户id："+seckillEvent.getSeckillId()+","+seckillEvent.getUserId());
    }
}
