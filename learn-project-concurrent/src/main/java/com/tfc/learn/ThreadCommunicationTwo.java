package com.tfc.learn;

/**
 * CAS
 */
public class ThreadCommunicationTwo {
	enum Ready {
		T1, T2
	};

	static volatile Ready current = Ready.T1;

	public static void main(String[] args) {
		String content1 = "123456";
		String content2 = "abcdef";
		char[] contentArray1 = content1.toCharArray();
		char[] contentArray2 = content2.toCharArray();
		Thread t1 = new Thread(() -> {
			for (char t : contentArray1) {
				while (current != Ready.T1) {
				}
				System.err.println(t);
				current = Ready.T2;
			}
		});
		Thread t2 = new Thread(() -> {
			for (char t : contentArray2) {
				while (current != Ready.T2) {
				}
				System.err.println(t);
				current = Ready.T1;
			}
		});
		t1.start();
		t2.start();
	}

}
