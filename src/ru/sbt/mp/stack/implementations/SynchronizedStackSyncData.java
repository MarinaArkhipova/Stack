package ru.sbt.mp.stack.implementations;

import java.util.ArrayList;

/**
 * Created by marina on 10.12.15.
 */
public class SynchronizedStackSyncData<E> implements IStack<E> {
    private final ArrayList<E> data;

    public SynchronizedStackSyncData() {
        this.data = new ArrayList<>();
    }

    @Override
    public void push(E element) throws IllegalStateException {
        synchronized (data) {
            data.add(element);
        }
    }

    @Override
    public E pop() throws IllegalStateException {
        synchronized (data) {
            E element = data.get(data.size() - 1);
            data.remove(data.size() - 1);
            return element;
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (data) {
            return data.isEmpty();
        }
    }
}
