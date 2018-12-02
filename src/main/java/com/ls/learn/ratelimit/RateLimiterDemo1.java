package com.ls.learn.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/8 9:32
 */


/**
 该例子是多个线程依次执行，限制每2秒最多执行一个。运行看结果
1、进行限流
 等待时间：0.0
 当前线程为：pool-1-thread-1；用户id为：0
 等待时间：1.988099
 当前线程为：pool-1-thread-1；用户id为：1
 等待时间：1.997969
 当前线程为：pool-1-thread-1；用户id为：2
 等待时间：1.999895
 当前线程为：pool-1-thread-1；用户id为：3
 等待时间：1.999932
 当前线程为：pool-1-thread-1；用户id为：4
 等待时间：2.000215
 当前线程为：pool-1-thread-1；用户id为：5
 等待时间：1.998741
 当前线程为：pool-1-thread-1；用户id为：6
 等待时间：2.000362
 当前线程为：pool-1-thread-1；用户id为：7
 等待时间：2.000325
 当前线程为：pool-1-thread-1；用户id为：8
 等待时间：2.000452
 主线程结束
 当前线程为：pool-1-thread-1；用户id为：9


 2、去掉限流的结果：
 主线程结束
 当前线程为：pool-1-thread-1；用户id为：0
 当前线程为：pool-1-thread-5；用户id为：4
 当前线程为：pool-1-thread-4；用户id为：3
 当前线程为：pool-1-thread-9；用户id为：8
 当前线程为：pool-1-thread-8；用户id为：7
 当前线程为：pool-1-thread-2；用户id为：1
 当前线程为：pool-1-thread-6；用户id为：5
 当前线程为：pool-1-thread-10；用户id为：9
 当前线程为：pool-1-thread-3；用户id为：2
 当前线程为：pool-1-thread-7；用户id为：6

 结论：
 进行限流的时候整个线程池维持一个线程即可，而不限流的情况下，需要维护10个线程
 我们限制了2秒放行一个，可以看到第一个是直接执行了，后面的每2秒会放行一个。
 rateLimiter.acquire()该方法会阻塞线程，直到令牌桶中能取到令牌为止才继续向下执行，并返回等待的时间。


 问题：为什么主线程和子线程都运行完了还没有退出主程序？


 */
public class RateLimiterDemo1 {

    public static void main(String[] args) {
        //0.5代表一秒最多多少个
        RateLimiter rateLimiter = RateLimiter.create(0.5);
        List<Runnable> tasks = new ArrayList<Runnable>();
        for (int i = 0; i < 10; i++) {
            tasks.add(new UserRequest(i));
        }
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (Runnable runnable : tasks) {
            System.out.println("等待时间：" + rateLimiter.acquire());
            threadPool.execute(runnable);
        }
        System.out.println("主线程结束");
    }

    private static class UserRequest implements Runnable {
        private int id;

        public UserRequest(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println("当前线程为："+Thread.currentThread().getName()+"；用户id为："+id);
        }
    }

}
