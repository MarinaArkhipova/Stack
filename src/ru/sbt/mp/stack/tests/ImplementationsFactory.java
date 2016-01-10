package ru.sbt.mp.stack.tests;

import ru.sbt.mp.stack.implementations.*;

import java.util.ArrayList;

/**
 * Created by km on 07.01.16.
 */
public class ImplementationsFactory {

    private  ArrayList<IStack> implementations = new ArrayList();
    private SynchronizedStackSyncData syncDataIml = new SynchronizedStackSyncData();
    private  SynchronizedStackSyncMethods syncMethodsImpl = new SynchronizedStackSyncMethods();
    private  SynchronizedStackSyncObject syncObjectImpl = new SynchronizedStackSyncObject();
    private  SynchronizedStackUnifiedLock unifiedLockImpl = new SynchronizedStackUnifiedLock();
    private  StandardStack standardImpl = new StandardStack();

    public ArrayList<IStack> getImplementations() {
        implementations.add(syncDataIml);
        implementations.add(syncMethodsImpl);
        implementations.add(syncObjectImpl);
        implementations.add(unifiedLockImpl);
        implementations.add(standardImpl);
        return implementations;
    }

}
