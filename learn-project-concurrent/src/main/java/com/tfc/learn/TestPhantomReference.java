package com.tfc.learn;

import com.google.common.collect.Lists;
import com.tfc.learn.lock.TestAtomic;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestPhantomReference {
    public static final List<Object> LIST= Lists.newArrayList();
    public static ReferenceQueue<Object> referenceQueue=new ReferenceQueue<>();

    public void collect(){
        while(true){
            Object o=referenceQueue.poll();
            if(o!=null){
                System.out.println("开始回收堆外内存，因为对象："+o+"被gc回收了");
                System.exit(0);
            }
        }

    }


    public static void main(String[] args) {
        PhantomReference<Object> phantomReference=new PhantomReference<>(new Object(),referenceQueue);
        System.out.println(phantomReference.get());

//        new Thread(()->{
//            while(true){
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                byte[] bytes=new byte[1*1024*1024];
//                LIST.add(bytes);
//                System.out.println(phantomReference.get());
//            }
//        }).start();
//
//        new Thread(new TestPhantomReference()::collect).start();
    }
}
