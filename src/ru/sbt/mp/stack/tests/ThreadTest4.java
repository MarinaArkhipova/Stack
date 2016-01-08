package ru.sbt.mp.stack.tests;

import ru.sbt.mp.stack.implementations.IStack;
import ru.sbt.mp.stack.implementations.SynchronizedStackUnifiedLock;

/**
 * Created by km on 27.12.15.
 */
public class ThreadTest4 {
    final static IStack<Integer> syncronizedStack = new SynchronizedStackUnifiedLock<>();

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
                try {
                    syncronizedStack.pop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    static Runnable r2 = new Runnable() {
        public void run() {
            for (int i = 0; i < iterations; i++) {
                syncronizedStack.push(2);
                try {
                    syncronizedStack.pop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public static void main(String[] arg) {
        Thread thr1 = new Thread(r1);
        Thread thr2 = new Thread(r2);
        thr1.start();
        thr2.start();
        while (thr1.isAlive() || thr2.isAlive()) {

        }
        checkIfPassed();
    }
}
