package com.tfc.learn;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author xiefei
 *
 * 线程可见性测试
 *
 *
 */
public class LessonTwo {
    private static boolean stopRequested=false;

    public void  run(){
        while(!stopRequested) {
            System.err.println(1);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        new Thread(new LessonTwo()::run,"testThread").start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }

}
