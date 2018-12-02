package com.ls.demo.sse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/10 19:19
 */
@Controller
@RequestMapping("/sse")
public class SseEmitterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SseEmitterController.class);


    ThreadPoolExecutor mvcTaskExecutor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(5));


    @Autowired
    private SSEComponent sseComponent;

    @RequestMapping("")
    public String helloworld()  {
        return "sse";
    }

    @GetMapping("/push")
    public SseEmitter sseDemo() throws InterruptedException {
        final SseEmitter emitter = new SseEmitter(0L); //timeout设置为0表示不超时
//        AsyncRuntime.getAsyncThreadPool().execute(new Runnable() {
        mvcTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = sseComponent.addSseUser("", emitter);
                    emitter.send("当前时间："+s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return emitter;
    }
}
