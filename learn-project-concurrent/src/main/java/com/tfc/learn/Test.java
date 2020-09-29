package com.tfc.learn;

/**
 * DCL µ¥Àý
 */
public class Test {

	private volatile static Test test;

	public static final Test getInstance(){
		if(test==null){
			synchronized (Test.class){
				if(test==null){
					test=new Test();
				}
				return test;
			}
		}
		return test;
	}
	public static void main(String[] args) {
		ThreadLocal<Object> tl=new ThreadLocal<Object>();
		Thread a = new Thread(() -> {
			System.err.println(Test.getInstance());
		});
		a.start();
	}
}
