package com.tfc.learn;

import java.util.concurrent.TimeUnit;

/**
 * volatile¿É¼ûÐÔ
 * @author oracle
 *
 */
public class LessonTwo {
	private boolean running = true;

	public void serverRun() {
		System.err.println("start...");
		while (running) {
			// doSth
		}
		System.err.println("end...");
	}

	public static void main(String[] args) throws InterruptedException {
		LessonTwo t = new LessonTwo();
		new Thread(t::serverRun, "t1").start();
		TimeUnit.SECONDS.sleep(1);
		t.running = false;
		System.err.println("endMain");
	}

}
