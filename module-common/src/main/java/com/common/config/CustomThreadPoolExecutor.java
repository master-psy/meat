package com.common.config;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 饿汉式单例模式
 *
 * @author Administrator
 * @Desc 自定义线程池
 */
public class CustomThreadPoolExecutor {
    /**
     * corePoolSize - 保留在池中的线程数，即使它们是空闲的，除非设置allowCoreThreadTimeOut
     * maximumPoolSize – 池中允许的最大线程数
     * keepAliveTime – 当线程数大于核心时，这是多余的空闲线程在终止前等待新任务的最长时间。
     * unit – keepAliveTime参数的时间单位
     * workQueue – 用于在执行任务之前保存任务的队列。此队列将仅保存由execute方法提交的Runnable任务。
     * threadFactory – 执行器创建新线程时使用的工厂
     * handler – 由于达到线程边界和队列容量而阻塞执行时使用的处理程序
     */
    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 5, 30, TimeUnit.MINUTES, new LinkedBlockingQueue<>(2048), new CustomFactory(), new CustomRejectedExecutionHandler());

    /**
     * 私有单例模式,通过getInstance实例化
     */
    private CustomThreadPoolExecutor() {
    }

    /**
     * 唯一对外接口获取线程池
     *
     * @return
     */
    public static ExecutorService getInstance() {
        return pool;
    }
}

/**
 * 自定义执行器创建新线程时使用的工厂
 */
@Slf4j
class CustomFactory implements ThreadFactory {
    //创建的线程个数,原子计数器
    private AtomicInteger count = new AtomicInteger(0);


    @Override
    public Thread newThread(Runnable r) {
        log.info("Executor new thread init()");
        Thread t = new Thread(r);
        String threadName = CustomThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);
        t.setName(threadName);
        return t;
    }
}

/**
 * 默认有四种实现
 * CallerRunsPolicy:被拒绝任务的处理程序，直接在execute方法的调用线程中运行被拒绝的任务，除非执行程序已关闭，在这种情况下，任务将被丢弃
 * AbortPolicy:抛出RejectedExecutionException的被拒绝任务的处理程序
 * DiscardPolicy:被拒绝任务的处理程序，它默默地丢弃被拒绝的任务
 * DiscardOldestPolicy:拒绝任务的处理程序，丢弃最旧的未处理请求，然后重试execute ，除非执行程序被关闭，在这种情况下任务被丢弃
 * <p>
 * 自定义线程池拒绝策略
 */
@Slf4j
class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            log.info("rejectedExecution try put into the BlockingQueue");
            //尝试重新放入队列
            executor.getQueue().put(r);
            //放入成功
            log.info("succeed put into the BlockingQueue");
        } catch (InterruptedException e) {
            //放入失败
            log.info("异常", e);
        }
    }
}
