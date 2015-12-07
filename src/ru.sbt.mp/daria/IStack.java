package ru.sbt.mp.daria;

/**
 * Created by daria on 08.12.15
 */

public interface IStack<E> {
    public void push(E element) throws IllegalStateException;

    public E pop() throws IllegalStateException;

    public boolean isEmpty();
}
