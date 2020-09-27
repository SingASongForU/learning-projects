package com.tfc.learn;

import java.util.concurrent.locks.LockSupport;

public class ThreadCommunicationThree {
	static Thread t1,t2,t3;
	public static void main(String[] args) {
		String content1="123456";
		String content2="abcdef";
		String content3="ABCDEF";
		char[] contentArray1=content1.toCharArray();
		char[] contentArray2=content2.toCharArray();
		char[] contentArray3=content3.toCharArray();
		t1=new Thread(()->{
			for(char t:contentArray1) {
				System.err.println(t);
				LockSupport.unpark(t2);
				LockSupport.park();
			}
		},"t1") ;
		t2=new Thread(()->{
			for(char t:contentArray2) {
				LockSupport.park();
				System.err.println(t);
				LockSupport.unpark(t3);
			}
		},"t2") ;
		t3=new Thread(()->{
			for(char t:contentArray3) {
				LockSupport.park();
				System.err.println(t);
				LockSupport.unpark(t1);
			}
		},"t3") ;
		t1.start();
		t2.start();
		t3.start();
	}


}
