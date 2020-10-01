package com.tfc.learn.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Xief
 */
public class DeadLock {
    private static Object lock1=new Object();
    private static Object lock2=new Object();
    public void hello(){
        synchronized (lock1){
            System.out.println("I am hello,I get lock1");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am hello,I wait lock2");
            synchronized (lock2){
                System.err.println("hello");
            }
        }
    }
    public void world(){
        synchronized (lock2){
            System.out.println("I am world,I get lock2");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am world,I wait lock1");
            synchronized (lock1){
                System.err.println("world");
            }
        }
    }
    public static final void main(String[] args){
        new Thread(new DeadLock()::hello).start();
        new Thread(new DeadLock()::world).start();
    }
}
