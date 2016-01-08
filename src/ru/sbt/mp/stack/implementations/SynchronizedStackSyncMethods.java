package ru.sbt.mp.stack.implementations;

import ru.sbt.mp.stack.implementations.IStack;

import java.util.ArrayList;

/**
 * Created by daria on 06.12.15
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

    /**
     * Created by marina on 10.12.15.
     */
    public static class SynchronizedStackSyncData<E> implements IStack<E> {
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
}
