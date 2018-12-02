package com.ls.demo.queue.jvm;

import com.ls.demo.bean.SuccessKilled;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/13 23:39
 * 此类在spring容器启动后执行，通过设置死循环，来一直检测是否有生产者是否产生了消息
 */
@Component
public class QueueConsumerApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while(true){
            //进程内队列
            SuccessKilled kill = SeckillQueue.getMailQueue().consume();
            if(kill!=null){
                //进入秒杀流程。。。
            }
        }
    }
}
