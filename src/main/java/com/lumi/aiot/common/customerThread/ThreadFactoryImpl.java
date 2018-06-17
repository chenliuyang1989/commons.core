package com.lumi.aiot.common.customerThread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义线程factory，命名清晰，便于后去管理
 * @author eric
 */
public class ThreadFactoryImpl implements ThreadFactory {

    private final AtomicLong threadIndex = new AtomicLong(0);

    private final String threadNamePrefix;

    /**
     * 自定义线程前缀
     * @param threadNamePrefix
     */
    public ThreadFactoryImpl(final String threadNamePrefix) {

        this.threadNamePrefix = threadNamePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, threadNamePrefix + this.threadIndex.incrementAndGet());

    }
}
