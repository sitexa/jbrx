package com.sitexa.tools.thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadExecutor {

    public static void main(String[] args) throws InterruptedException {

        Runnable myRunnable = () -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 2, TimeUnit.SECONDS, new SynchronousQueue<>());

        executor.execute(myRunnable);
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        System.out.println("---先开三个---");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        System.out.println("---再开三个---");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());
        Thread.sleep(8000);
        System.out.println("----8秒之后----");
        System.out.println("核心线程数" + executor.getCorePoolSize());
        System.out.println("线程池数" + executor.getPoolSize());
        System.out.println("队列任务数" + executor.getQueue().size());


    }


}
