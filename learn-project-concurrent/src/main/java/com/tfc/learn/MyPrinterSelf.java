package com.tfc.learn;

public class MyPrinterSelf implements Runnable{
	private Object selflock;
	private Object lastlock;
	private String name;

	public MyPrinterSelf(Object selflock, Object lastlock, String name) {
		super();
		this.selflock = selflock;
		this.lastlock = lastlock;
		this.name = name;
	}

	@Override
	public void run() {
		int count=10;
		while(count>0) {
			synchronized(lastlock) {
				synchronized(selflock) {
					System.err.print(name);
					count--;
					selflock.notify();
				}
				try {
					lastlock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Object locka=new Object();
		Object lockb=new Object();
		Object lockc=new Object();
		
		MyPrinterSelf ma=new MyPrinterSelf(locka, lockc, "A");
		MyPrinterSelf mb=new MyPrinterSelf(lockb, locka, "B");
		MyPrinterSelf mc=new MyPrinterSelf(lockc, lockb, "C");
		Thread ta=new Thread(ma);
		Thread tb=new Thread(mb);
		Thread tc=new Thread(mc);
		ta.start();
		Thread.sleep(10);
		tb.start();
		Thread.sleep(10);
		tc.start();
		Thread.sleep(10);
		
		
	}

}
