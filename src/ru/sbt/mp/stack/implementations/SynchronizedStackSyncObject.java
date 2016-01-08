package ru.sbt.mp.stack.implementations;

import ru.sbt.mp.stack.implementations.IStack;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by km on 27.12.15.
 */
public class SynchronizedStackSyncObject<E> implements IStack<E> {
    private final ArrayList<E> data;

    public final static Object obj = new Object();

    public SynchronizedStackSyncObject() {
        this.data = new ArrayList<>();
    }

    @Override
    public void push(E element) throws IllegalStateException {
        synchronized (obj) {
            data.add(element);
        }
    }

    @Override
    public E pop() throws IllegalStateException, InterruptedException {
        E element;
        synchronized (obj) {
            element = data.get(data.size() - 1);
            data.remove(data.size() - 1);
        }
        return element;
    }

    @Override
    public boolean isEmpty() {
        Lock lock = new ReentrantLock();
        lock.lock();
        Boolean isEmpty = data.isEmpty();
        lock.unlock();
        return isEmpty;
    }
}
