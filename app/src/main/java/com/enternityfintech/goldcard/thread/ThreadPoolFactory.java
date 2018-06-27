package com.enternityfintech.goldcard.thread;

/**
 * Created by cgy
 * 2018/6/22  13:55
 * 线程池工厂
 */
public class ThreadPoolFactory {

    static ThreadPoolProxy normalPool;
    static ThreadPoolProxy downloadPool;

    /**
     * 得到一个普通的线程池
     * @return
     */
    public static ThreadPoolProxy getNormalPool() {
        if (normalPool == null) {
            synchronized (ThreadPoolFactory.class) {
                if (normalPool == null) {
                    normalPool = new ThreadPoolProxy(5, 5, 3000);

                }
            }
        }
        return normalPool;
    }

    /**
     * 得到一个下载的线程池
     * @return
     */
    public static ThreadPoolProxy getDownloadPool() {
        if (downloadPool == null) {
            synchronized (ThreadPoolFactory.class) {
                if (downloadPool == null) {
                    downloadPool = new ThreadPoolProxy(3, 3, 3000);
                }
            }
        }
        return downloadPool;
    }
}
