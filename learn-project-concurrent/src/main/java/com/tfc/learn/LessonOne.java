package com.tfc.learn;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author xiefei
 * <p>
 * java对象内存布局
 */
public class LessonOne {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        print(o);
//		printSyn(o);

    }

    public static void print(Object o) {
        System.err.println(ClassLayout.parseInstance(o).toPrintable());
    }

    public static void printSyn(Object o) {
        synchronized (o) {
            System.err.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

}
