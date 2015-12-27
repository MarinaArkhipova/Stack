package ru.sbt.mp.stack;

/**
 * Created by stack on 08.12.15
 */

public interface IStack<E> {
    public void push(E element) throws IllegalStateException;

    public E pop() throws IllegalStateException, InterruptedException;

    public boolean isEmpty();
}
