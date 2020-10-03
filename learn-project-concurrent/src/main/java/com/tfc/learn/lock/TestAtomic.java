package com.tfc.learn.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic {
    public static final AtomicInteger count=new AtomicInteger();
    public static final CountDownLatch countDownLatch=new CountDownLatch(50);

    public void run(){
        for(int i=0;i<1000_0000;i++){
            count.incrementAndGet();
        }
        countDownLatch.countDown();
    }
    public static void main(String[] args) throws InterruptedException {
        long begin=System.currentTimeMillis();
        for(int i=0;i<50;i++){
            Thread t=new Thread(new TestAtomic()::run);
            t.start();
        }
        countDownLatch.await();
        System.err.println(System.currentTimeMillis()-begin);
        System.err.println(count.get());
    }
}
