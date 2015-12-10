package ru.sbt.mp.stack;

/**
 * Created by stack on 08.12.15
 */
public class ThreadTest {
    final static IStack<Integer> syncronizedStack = new SynchronizedStack<>();

    static Runnable r1 = new Runnable() {
        public void run() {
            try {
                while (true) {
                    syncronizedStack.push(1);
                    Thread.sleep(1000L);
                    System.out.println(syncronizedStack.pop());
                }
            } catch (InterruptedException iex) {}
        }
    };
    static Runnable r2 = new Runnable() {
        public void run() {
            try {
                while (true) {
                    syncronizedStack.push(2);
                    Thread.sleep(2000L);
                    System.out.println(syncronizedStack.pop());
                }
            } catch (InterruptedException iex) {}
        }
    };

    public static void main(String[] arg) {
        Thread thr1 = new Thread(r1);
        Thread thr2 = new Thread(r2);
        thr1.start();
        thr2.start();
    }
}
