package com.tfc.learn;

/**
 * -XX:+DoEscapeAnalysis -XX:-EliminateLocks 关闭
 *  
 * -XX:+DoEscapeAnalysis -XX:+EliminateLocks 开启
 * 
 * 
 * 锁消除和锁粗化
 * @author oracle
 *
 */
public class LessonThree {
	public static void main(String[] args) {
		LessonThree lt = new LessonThree();
		String str1 = "hello";
		String str2 = "world";
	    sayTime(lt, str1, str2);
	}

	private static void sayTime(LessonThree lt, String str1, String str2) {
		long begin=System.currentTimeMillis();
		for(int i=0;i<1_0000_0000;i++) {
			lt.lockEliminate(str1, str2);
		}
		System.err.println("Spend:"+(System.currentTimeMillis()-begin)+"ms");
	}

	public void lockEliminate(String str1, String str2) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(str1).append(str2);
	}

	public void lock() {
		Object lock = new Object();
		for (int i = 0; i < 10000; i++) {
			synchronized (lock) {
				// do sth.
			}
		}
	}

	public void lockStrong() {
		Object lock = new Object();
		synchronized (lock) {
			for (int i = 0; i < 10000; i++) {
				// do sth.
			}
		}
	}
}
