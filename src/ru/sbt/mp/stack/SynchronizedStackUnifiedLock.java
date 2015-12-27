package ru.sbt.mp.stack;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by km on 27.12.15.
 */
public class SynchronizedStackUnifiedLock<E> implements IStack<E> {

    private final ArrayList<E> data;

    private Lock lock;

    public SynchronizedStackUnifiedLock() {
        this.data = new ArrayList<>();
        lock = new ReentrantLock();
    }

    @Override
    public void push(E element) throws IllegalStateException {
        lock.lock();
        data.add(element);
        lock.unlock();
    }

    @Override
    public E pop() throws IllegalStateException {
        lock.lock();
        E element;
        if (isEmpty()){
            throw new IllegalStateException();
        }
        else element = data.remove(data.size() - 1);
        lock.unlock();
        return element;
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        Boolean isEmpty = data.isEmpty();
        lock.unlock();
        return isEmpty;
    }
}
