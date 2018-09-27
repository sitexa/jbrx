package com.sitexa.tools.thread;

public class ExecutorClient {

    public static void main(String[] args) throws InterruptedException {

        ExecuterDemo t = new ExecuterDemo();

        t.asynTask();

        int j = 0;
        while (true) {
            if (j++ > 10000) {
                break;
            }
        }

        t.cancel();

        System.out.println("------------main end-----------");
    }

}
