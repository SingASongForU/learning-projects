package com.tfc.learn;

import org.openjdk.jol.info.ClassLayout;
/**
 * 对象在内存中的布局
 * @author oracle
 *
 */
public class LessonOne {
	public static void main(String[] args) throws InterruptedException {
		Object o=new Object();
//		print(o);
//		o.hashCode();
//		print(o);
		printSyn(o);

	}
	
	public static void print(Object o) {
			System.err.println(ClassLayout.parseInstance(o).toPrintable());
	}
	
	public static void printSyn(Object o) {
		synchronized(o) {
			System.err.println(ClassLayout.parseInstance(o).toPrintable());
		}
	}

}
