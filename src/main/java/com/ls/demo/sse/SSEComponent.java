package com.ls.demo.sse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/11 10:01
 */
@Component
public class SSEComponent {

    private final Lock lock = new ReentrantLock();
    private final Lock refreshLock = new ReentrantLock();
    private Logger logger = LoggerFactory.getLogger(SSEComponent.class);

    private static final List<SseEmitter> sseList = new CopyOnWriteArrayList<SseEmitter>();
    /**
     * 保存链接到服务器上的用户，便于刷新发送消息
     * @param id
     * @param sse
     * @return
     */
    public String addSseUser(String id,SseEmitter sse) {
        String result = "";
        //不起作用，而且也没有把关闭掉的链接去除
//        sse.onCompletion(() -> {
//                    synchronized (this.sseList) {
//                        this.sseList.remove(sse);
//                    }});
//
//        sse.onTimeout(() -> {
//            sse.complete();
//        });
        sseList.add(sse);
        result = new Date().toString();
        return result;
    }

    /**
     * 定时刷新任务
     */
    public static void refreshMeassge(){
        System.out.println("开始执行刷新任务，当前的sseList大小："+sseList.size());
            for (SseEmitter sse:sseList) {
                try {
                    sse.send("666："+new Date().toString());
                } catch (Exception e) {
                    //客户端关闭浏览器会报IOException,此时移除sse即可
                    //e.printStackTrace();
                    //sse.complete();
                    sseList.remove(sse);
                }
            }
    }

}
