package ru.sbt.mp.stack.tests;

import ru.sbt.mp.stack.implementations.*;

import java.util.ArrayList;

/**
 * Created by km on 07.01.16.
 */
public class ImplementationsFactory {

    ArrayList<IStack> implementations = new ArrayList();


    public ArrayList<IStack> getAllImplementations() {
        implementations.add(new SynchronizedStackSyncData<>());
        implementations.add(new SynchronizedStackSyncMethods<>());
        implementations.add(new SynchronizedStackSyncObject<>());
        implementations.add(new SynchronizedStackUnifiedLock<>());

        return implementations;
    }
}
