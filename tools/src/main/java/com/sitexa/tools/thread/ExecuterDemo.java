package com.sitexa.tools.thread;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ExecuterDemo {

    private ExecutorService executor = Executors.newFixedThreadPool(1);

    private long t1;
    private long t2;

    void asynTask() {

        t1 = new Date().getTime();

        executor.submit(() -> {
            System.out.println("asyncTask begin");

            try {
                Thread.sleep(10000);//方便观察结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int sum = 0;
            for (int i = 0; i < 100000; i++) {
                sum += i;
            }
            t2 = new Date().getTime();
            System.out.println("asyncTask period:" + (t2 - t1) );
        });

    }

    public void cancel() {
        System.out.println("Executor shutdown");
        executor.shutdown();
    }
}
