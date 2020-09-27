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
                  //�����ڴ˶���������ϵȴ��ĵ����̣߳����ȴ���self�������̣߳����������̶߳��ڴ˶����ϵȴ��������ѡ��������һ���̡߳�
                    selfLock.notify(); 
                } 
                try {   
                	//self�����������ѵ��̴߳�ʱ���Ը�self�����ˡ�  
                	//���߳���ʱ�ͷ�lastLock�������ȴ��ٴλ��lastLock������Ȼ��ִ���������䡣��ʱlastLock����Ҫ������
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
