package com.tfc.learn.lock;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Xief
 */
public class ConcurrentList {
    private static volatile List<String> tempList = Lists.newArrayList();

    public void add() {
        while(true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tempList.add("hello");
            System.err.println(Thread.currentThread().getName()+":"+tempList.size());
        }
    }

    public void check(){
        while(tempList.size()<5){
        }
        System.err.println("quit");
    }

    public static void main(String[] args) {
        new Thread(new ConcurrentList()::add,"thread1").start();
        new Thread(new ConcurrentList()::check,"thread2").start();

    }


}
