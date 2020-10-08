package com.tfc.learn;

/**
 * 内存伪共享对性能的影响
 *
 * @author oracle
 */
public class LessonFour {
    private static class Test {
        @SuppressWarnings("unused")
        public volatile long x = 0L;
    }

    public static Test[] arr = new Test[2];

    static {
        arr[0] = new Test();
        arr[1] = new Test();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 1_0000_0000; i++) {
                arr[0].x = i;
            }
        });
        Thread t2 = new Thread(() -> {
            for (long i = 0; i < 1_0000_0000; i++) {
                arr[1].x = i;
            }
        });
        final long begin = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.err.println("Spend:" + (System.nanoTime() - begin) / 100_0000 + "ms");
    }

}
