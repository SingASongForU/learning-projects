package com.tfc.learn;

public class ThreadConmmunicationSix {
	static private Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		String content1 = "123456";
		String content2 = "abcdef";
		char[] contentArray1 = content1.toCharArray();
		char[] contentArray2 = content2.toCharArray();
		Thread t1 = new Thread(() -> {
			synchronized (lock) {
				for (char t : contentArray1) {
					lock.notify();
					System.err.println(t);
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				lock.notify();
			}
		}, "t1");
		Thread t2 = new Thread(() -> {
			synchronized (lock) {
				for (char t : contentArray2) {
					lock.notify();
					System.err.println(t);
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				lock.notify();
			}
		}, "t2");
		t1.start();
		t2.start();
	}

}
