package com.tfc.learn.lock;

import java.util.concurrent.TimeUnit;

/**
 * jetty bug 字符串作为锁对象的隐患
 *
 * @author xiefei
 */
public class JettyDeadLock {
    private String lock1="myLock";
    private String lock2="myLock";

    public void lock1(){
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName()+" get lock1");
            while(true){
                //do sth.
            }
        }
    }

    public void lock2(){
        synchronized (lock2){
            System.out.println(Thread.currentThread().getName()+" get lock2");
            while(true){
                //do sth
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JettyDeadLock jettyDeadLock=new JettyDeadLock();
        new Thread(jettyDeadLock::lock1,"thread1").start();
        new Thread(jettyDeadLock::lock2,"thread2").start();
    }
}
