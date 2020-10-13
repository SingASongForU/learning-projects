package com.tfc.learn;

import java.lang.ref.WeakReference;

public class TestWeakReference {
    public static void main(String[] args) {
        WeakReference<Object> weakReference=new WeakReference<Object>(new Object());
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }
}
