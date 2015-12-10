package ru.sbt.mp.stack;

/**
 * Created by marina on 10.12.15.
 */
public class ThreadTest2 {
    final static IStack<Integer> syncronizedStack = new SynchronizedStack2<>();

    static final int iterations = 100000;

    public static void checkIfPassed() {
        if (syncronizedStack.isEmpty()) {
            System.out.println("Test passed");
        }
        else System.out.println("Test not passed");
    }

    static Runnable r1 = new Runnable() {
        public void run() {
            for (int i = 0; i < iterations; i++) {
                syncronizedStack.push(1);
                syncronizedStack.pop();
            }
            checkIfPassed();
        }
    };
    static Runnable r2 = new Runnable() {
        public void run() {
            for (int i = 0; i < iterations; i++) {
                syncronizedStack.push(2);
                syncronizedStack.pop();
            }
            checkIfPassed();
        }
    };

    public static void main(String[] arg) {
        Thread thr1 = new Thread(r1);
        Thread thr2 = new Thread(r2);
        thr1.start();
        thr2.start();
    }
}
