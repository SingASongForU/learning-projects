package com.tfc.learn;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class ThreadConmmunicationFive {
	static byte[] msg="yourturn".getBytes();
	
	public static void main(String[] args) {
		String content1="123456";
		String content2="abcdef";
		char[] contentArray1=content1.toCharArray();
		char[] contentArray2=content2.toCharArray();
		
		PipedInputStream pis1=new PipedInputStream();
		PipedInputStream pis2=new PipedInputStream();
		PipedOutputStream pos1=new PipedOutputStream();
		PipedOutputStream pos2=new PipedOutputStream();
		
		try {
			pos1.connect(pis2);
			pis1.connect(pos2);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Thread t1=new Thread(()->{
			for(char t:contentArray1) {
				System.err.println(t);
				try {
					pos1.write(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
				byte[] content=new byte[8];
				try {
					pis1.read(content);
				} catch (IOException e) {
					try {
						pos1.close();
						pis1.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
		},"t1") ;
		
		Thread t2=new Thread(()->{
			for(char t:contentArray2) {
				byte[] content=new byte[8];
				try {
					pis2.read(content);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.err.println(t);
				try {
					pos2.write(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		},"t2") ;
		t1.start();
		t2.start();
	}

}
