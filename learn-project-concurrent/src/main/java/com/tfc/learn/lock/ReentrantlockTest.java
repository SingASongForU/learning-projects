package com.tfc.learn.lock;

import java.sql.Time;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Xief
 */
public class ReentrantlockTest {
    public static int count = 0;
    ReentrantLock lock = new ReentrantLock();
    public static CountDownLatch countDownLatch = new CountDownLatch(10);

    public void run() {
        for (int i = 0; i < 10000000; i++) {
            try {
                lock.lock();
                count++;
            } finally {
                lock.unlock();
            }
        }
        countDownLatch.countDown();
        System.err.println(Thread.currentThread().getName() + " countdown");
    }

    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        ReentrantlockTest reentrantlockTest = new ReentrantlockTest();
        for (int i = 0; i < 10; i++) {
            new Thread(reentrantlockTest::run, "Thread" + i).start();
        }
        countDownLatch.await(5, TimeUnit.SECONDS);
        System.err.println(count);
        System.err.println("spend:" + (System.currentTimeMillis() - begin));
    }
}
