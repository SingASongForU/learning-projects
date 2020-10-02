package com.tfc.learn.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Xief
 */
public class SynchronizedTest {
    public static int count=0;
    private Object lock=new Object();
    public static CountDownLatch countDownLatch=new CountDownLatch(10);
    public void run(){
        synchronized (lock){
            for(int i=0;i<10000000;i++){
                synchronized (lock){
                    count++;
                }
            }
        }
        countDownLatch.countDown();
        System.err.println(Thread.currentThread().getName()+" countDown");
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest synchronizedTest=new SynchronizedTest();
        long begin = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            new Thread(synchronizedTest::run, "Thread" + i).start();
        }
        countDownLatch.await();
        System.err.println(count);
        System.err.println("spend:" + (System.nanoTime() - begin));
    }
}
