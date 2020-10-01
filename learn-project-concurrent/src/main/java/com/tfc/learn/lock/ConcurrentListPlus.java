package com.tfc.learn.lock;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Xief
 */
public class ConcurrentListPlus {
    private static List<String> tempList = Lists.newArrayList();
    private static Object lock=new Object();

    public void add() {
        while(true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tempList.add("hello");
            if(tempList.size()==5){
                synchronized (lock){
                    lock.notify();
                }
            }
            System.err.println(Thread.currentThread().getName()+":"+tempList.size());
        }
    }

    public void check(){
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(tempList.size());
            System.err.println("quit");
        }

    }

    public static void main(String[] args) {
        new Thread(new ConcurrentList()::add,"thread1").start();
        new Thread(new ConcurrentList()::check,"thread2").start();

    }


}
