package com.tfc.learn;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * -Xms10M -Xmx10M -verbose:gc
 * @author Xief
 */
public class TestSoftReference {
    public static void main(String[] args) throws InterruptedException {
        byte[] temp = new byte[2 * 1024 * 1024];
        SoftReference<byte[]> sr = new SoftReference<byte[]>(temp);
        temp = null;
        TimeUnit.SECONDS.sleep(2);
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.err.println(sr.get());
        byte[] temp1 = new byte[1024 * 1024 * 3];
        System.err.println("Big Object temp1 create:" + temp1);
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.err.println(sr.get());
    }
}
