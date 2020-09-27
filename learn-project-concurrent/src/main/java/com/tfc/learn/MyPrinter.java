package com.tfc.learn;

public class MyPrinter implements Runnable {   
	
	private static final int TIMES=10;
  
    private Object lastLock;   
    private Object selfLock;  
    private String content;
   
  
    private MyPrinter(String content,Object lastLock, Object selfLock) {   
        this.lastLock = lastLock;   
        this.selfLock = selfLock;  
        this.content=content;
    }   
  
    @Override  
    public void run() {   
        int count =TIMES;   
        while (count > 0) {
            synchronized (lastLock) {   
                synchronized (selfLock) {   
                    System.out.print(content);   
                    count--;  
                  //唤醒在此对象监视器上等待的单个线程（即等待给self加锁的线程）。假如多个线程都在此对象上等待，则会挑选唤醒其中一个线程。
                    selfLock.notify(); 
                } 
                try {   
                	//self解锁，被唤醒的线程此时可以给self加锁了。  
                	//该线程暂时释放lastLock的锁，等待再次获得lastLock的锁，然后执行下面的语句。此时lastLock还需要被唤醒
                	lastLock.wait();   
                } catch (InterruptedException e) {   
                    e.printStackTrace();   
                }   
            }   
        } 
        synchronized (selfLock) { 
        	selfLock.notify();
        }
    }   
  
    public static void main(String[] args) throws Exception {   
        Object lock_a = new Object();   
        Object lock_b = new Object();   
        Object lock_c = new Object();   
        MyPrinter thread_a = new MyPrinter("A", lock_c, lock_a);   
        MyPrinter thread_b = new MyPrinter("B", lock_a, lock_b);   
        MyPrinter thread_c = new MyPrinter("C", lock_b, lock_c);   
        new Thread(thread_a).start();
        Thread.sleep(10);
        new Thread(thread_b).start();
        Thread.sleep(10);
        new Thread(thread_c).start();
        Thread.sleep(10);
    }   
}  
