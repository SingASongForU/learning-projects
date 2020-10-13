package com.tfc.learn;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public interface TestPhantomReference {
    ReferenceQueue<Object> referenceQueue=new ReferenceQueue<>();

    static void main(String[] args) {
        PhantomReference<Object> phantomReference=new PhantomReference<>(new Object(),referenceQueue);
        System.out.println(phantomReference.get());
    }
}
