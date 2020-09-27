package com.tfc.learn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadConmmunicationSeven {
	public static void main(String[] args) {
		String content1 = "123456";
		String content2 = "abcdef";
		char[] contentArray1 = content1.toCharArray();
		char[] contentArray2 = content2.toCharArray();
		ReentrantLock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Thread t1 = new Thread(() -> {
			try {
				lock.lock();
				for (char t : contentArray1) {
					System.err.println(t);
					condition2.signal();
					try {
						condition1.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				condition2.signal();
			} finally {
				lock.unlock();
			}
		}, "t1");
		Thread t2 = new Thread(() -> {

			try {
				lock.lock();
				for (char t : contentArray2) {
					System.err.println(t);
					condition1.signal();
					try {
						condition2.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				condition1.signal();
			} finally {
				lock.unlock();
			}
		}, "t2");
		t1.start();
		t2.start();
	}

}
