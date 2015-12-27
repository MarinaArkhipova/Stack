package ru.sbt.mp.stack;

import java.util.ArrayList;

/**
 * Created by stack on 06.12.15
 */
public class SynchronizedStackSyncMethods<E> implements IStack<E> {
    private final ArrayList<E> arrayList;

    public SynchronizedStackSyncMethods() {
        this.arrayList = new ArrayList<>();
    }

    @Override
    public synchronized void push(E element) throws IllegalStateException {
        arrayList.add(element);
    }

    @Override
    public synchronized E pop() throws IllegalStateException {
        if (isEmpty()){
            throw new IllegalStateException();
        }
        return arrayList.remove(arrayList.size() - 1);
    }

    @Override
    public synchronized boolean isEmpty() {
        return arrayList.isEmpty();
    }
}
