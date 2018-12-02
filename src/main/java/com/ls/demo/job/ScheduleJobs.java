package com.ls.demo.job;

import com.ls.demo.sse.SSEComponent;
import com.ls.demo.websocket.WebSocketServer;
import org.apache.logging.log4j.core.util.datetime.FastDateFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/10 16:39
 */
@Component
public class ScheduleJobs {
    public final static long SECOND = 1 * 1000;
    FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");


    //定时任务1秒钟输出一次
    @Scheduled(cron = "0/3 * * * * ?")
    public void cronJob() {
        System.out.println("[CronJob Execute]"+fdf.format(new Date()));
         try {
             //使用websocket实时推送信息
            //WebSocketServer.sendInfo(new Date().toString(), null);
             //使用sse实时推送信息
             //SSEComponent.refreshMeassge();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}

