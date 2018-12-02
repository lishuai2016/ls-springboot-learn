package com.ls.demo.sse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/11 9:42
 */
public class AsyncRuntime {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncRuntime.class);
    private static volatile ThreadPoolExecutor asyncThreadPool;

    public AsyncRuntime() {
    }

    public static ThreadPoolExecutor getAsyncThreadPool() {
        return getAsyncThreadPool(true);
    }

    public static ThreadPoolExecutor getAsyncThreadPool(boolean build) {
        if (asyncThreadPool == null && build) {
            Class var1 = AsyncRuntime.class;
            synchronized(AsyncRuntime.class) {
                if (asyncThreadPool == null && build) {
                    int coreSize = 10;
                    int maxSize = 200;
                    int queueSize = 256;
                    int keepAliveTime = 60000;
                    BlockingQueue<Runnable> queue = ThreadPoolUtils.buildQueue(queueSize);
                    NamedThreadFactory threadFactory = new NamedThreadFactory("COMMON-CB", true);
                    RejectedExecutionHandler handler = new RejectedExecutionHandler() {
                        private int i = 1;

                        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                            if (this.i++ % 7 == 0) {
                                this.i = 1;
                                if (AsyncRuntime.LOGGER.isWarnEnabled()) {
                                    AsyncRuntime.LOGGER.warn("Task:{} has been reject because of threadPool exhausted! pool:{}, active:{}, queue:{}, taskcnt: {}", new Object[]{r, executor.getPoolSize(), executor.getActiveCount(), executor.getQueue().size(), executor.getTaskCount()});
                                }
                            }

                            throw new RejectedExecutionException("Callback handler thread pool has bean exhausted");
                        }
                    };
                    asyncThreadPool = ThreadPoolUtils.newCachedThreadPool(coreSize, maxSize, keepAliveTime, queue, threadFactory, handler);
                }
            }
        }

        return asyncThreadPool;
    }
}
