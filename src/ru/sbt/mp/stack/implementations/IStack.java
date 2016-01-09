package ru.sbt.mp.stack.implementations;

/**
 * Created by daria on 08.12.15
 */

public interface IStack<E> {
    public void push(E element) throws IllegalStateException;

    public E pop() throws IllegalStateException, InterruptedException;

    public boolean isEmpty();

    public default String getShortName() {
        String fullName = getClass().toString();
        String shortName = fullName.substring(fullName.lastIndexOf('.')+1);
        return shortName;
    }
}
