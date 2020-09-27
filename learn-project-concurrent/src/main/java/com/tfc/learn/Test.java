package com.tfc.learn;

public class Test {
	public static void main(String[] args) {
		Thread a = new Thread(() -> {
			System.err.println(1);
		});
		a.start();
	}
}
