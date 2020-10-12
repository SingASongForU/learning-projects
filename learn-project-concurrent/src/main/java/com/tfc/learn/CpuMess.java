package com.tfc.learn;

/**
 * @author Xief
 */
public class CpuMess {
    int a = 0;
    int b = 0;
    int x = -1;
    int y = -1;

    /**
     * a=1 x=b b=2 y=a
     * <p>
     * a=1 b=2 x=b y=a
     * <p>
     * a=1 b=2 y=a x=b
     * <p>
     * b=2 y=a a=1 x=b
     * <p>
     * <p>
     * x=b y=a a=1 b=2
     */

    public void path1() {
        a = 1;
        x = b;
    }

    public void path2() {
        b = 2;
        y = a;
    }

    public boolean test() throws InterruptedException {
        a = b = 0;
        x = y = -1;

        Thread t1 = new Thread(() -> {
            path1();
        });
        Thread t2 = new Thread(() -> {
            path2();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        if (x == 0 && y == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <= 1000000000; i++) {
            CpuMess cpuMess = new CpuMess();
            boolean b = cpuMess.test();
            if (!b) {
                System.out.println(i);
            }
        }
    }
}
