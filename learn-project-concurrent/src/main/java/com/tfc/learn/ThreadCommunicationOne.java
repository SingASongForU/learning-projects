package com.tfc.learn;

import java.util.concurrent.locks.LockSupport;

public class ThreadCommunicationOne {
	static Thread t1=null, t2=null;
	public static void main(String[] args) throws InterruptedException {
		String content1="123456";
		String content2="abcdef";
		char[] contentArray1=content1.toCharArray();
		char[] contentArray2=content2.toCharArray();
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
				LockSupport.unpark(t1);
			}
		},"t2") ;
		t1.start();
		t2.start();
	}

}
