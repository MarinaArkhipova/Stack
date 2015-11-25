package  ru.sbt.mp;

import java.util.ArrayList;


public class Stack {
    public ArrayList<Object> data;

    public Stack() {
        data = new ArrayList<Object>();
    }

    public void push(Object element) {
        data.add(element);
    }

    public Object pop() {
        synchronized (this) {
            Object element = data.get(data.size() - 1);
            data.remove(data.size() - 1);
            return element;
        }
    }

    public static void main(String[] args) {

        Stack stack = new Stack();
        stack.push(5);
        stack.push(2);

        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }
}


