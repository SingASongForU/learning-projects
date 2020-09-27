package com.tfc.learn;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadCommunicationFour {
	static BlockingQueue<String> q1=new ArrayBlockingQueue<String>(1);
	static BlockingQueue<String> q2=new ArrayBlockingQueue<String>(1);
	
	
	public static void main(String[] args) {
		String content1="123456";
		String content2="abcdef";
		char[] contentArray1=content1.toCharArray();
		char[] contentArray2=content2.toCharArray();
		Thread t1=new Thread(()->{
			for(char t:contentArray1) {
				System.err.println(t);
				try {
					q1.put("ok");
					q2.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1") ;
		Thread t2=new Thread(()->{
			for(char t:contentArray2) {
                try {
					q1.take();
					System.err.println(t);
					q2.put("ok");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t2") ;
		t1.start();
		t2.start();
	}

}
