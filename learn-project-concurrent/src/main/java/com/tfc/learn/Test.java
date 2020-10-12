package com.tfc.learn;

/**
 * DCL单例+volatile
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
		Thread a = new Thread(() -> {
			System.err.println(Test.getInstance());
		});
		a.start();
	}
}
