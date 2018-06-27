package com.enternityfintech.goldcard.thread;




import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by cgy
 * 2018/6/22  13:56
 *
 * 创建线程池 执行任务 提交任务
 */
public class ThreadPoolProxy {

    ThreadPoolExecutor executor; //只需创建一次
    int corePoolSize;
    int maximumPoolSize;
    long keepAliveTime;

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
    }

    public ThreadPoolExecutor initThreadPoolExecutor() { //双重检查加锁
        if (executor == null) {
            synchronized (ThreadPoolProxy.class) {
                if (executor == null) {
                    TimeUnit unit = TimeUnit.MILLISECONDS; //毫秒
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();//无界队列
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();//任务数量超过时，丢弃任务 抛出异常


                    executor = new ThreadPoolExecutor(
                            corePoolSize,//核心线程数
                            maximumPoolSize,//最大线程数
                            keepAliveTime,//保持时间
                            unit,//保持时间对应的单位
                            workQueue,//缓存队列,阻塞队列
                            threadFactory,//线程工厂
                            handler //异常捕获器
                    );

                }
            }
        }
        return executor;
    }

    /**
     * 执行任务
     * @param task
     */
    public void execute(Runnable task) {
        initThreadPoolExecutor();
        executor.execute(task);
    }

    /**
     * 提交任务
     * @param task
     * @return
     */
    public Future<?> submit(Runnable task) {
        initThreadPoolExecutor();
        return executor.submit(task);
    }

    /**
     * 移除任务
     * @param task
     */
    public void removeTask(Runnable task) {
        initThreadPoolExecutor();
        executor.remove(task);
    }
}
