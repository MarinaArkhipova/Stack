package ru.sbt.mp.stack.implementations;

import java.util.Stack;

/**
 * Created by km on 09.01.16.
 */
public class StandardStack<E> implements IStack {

    private Stack standardStack = new Stack();

    @Override
    public void push(Object element) throws IllegalStateException {
        this.standardStack.push(element);
    }

    @Override
    public Object pop() throws IllegalStateException, InterruptedException {
        return this.standardStack.pop();
    }

    @Override
    public boolean isEmpty() {
        return this.standardStack.isEmpty();
    }
}
