package ru.sbt.mp.stack.tests;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mp.stack.implementations.IStack;

import java.io.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

import static java.lang.Long.MAX_VALUE;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.DAYS;

/**
 * Created by km on 07.01.16.
 */
public class TimeTest {

    private ImplementationsFactory factory;
    private ExecutorService executors;

    private final int THREAD_POOL_SIZE = 10;
    private final long NUMBER_OF_ITERATIONS = 100000;
    private final long REPETITION_NUMBER = 5;

    private PrintWriter printWriter;

    public TimeTest() throws IOException {
        String outputName = "report.txt";
        printWriter = new PrintWriter(new FileWriter(outputName, true), true);
    }

    @Override
    protected void finalize() throws Throwable {
        printWriter.close();
        super.finalize();
    }

    @Before
    public void prepare() {
        factory = new ImplementationsFactory();
    }

    @Test
    public void testStackPushAndPopOperations() {
        printWriter.println("\nTesting Push and Pop operations");
        for (IStack implementation : factory.getImplementations()) {
            measureTime(implementation, testPushAndPopOperations);
        }
    }

    @Test
    public void testStackPushOperations() {
        printWriter.println("\nTesting Push operation");
        for (IStack implementation : factory.getImplementations()) {
            measureTime(implementation, testPushOperation);
        }
    }

    public Function<IStack, Integer> testPushAndPopOperations = implementation -> {
        try {
            performPushAndPop(implementation);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    };

    public Function<IStack, Integer> testPushOperation = implementation -> {
        try {
            performPush(implementation);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    };

    public void measureTime(IStack implementation, Function<IStack, Integer> function) {
        long totalTime = 0;
        double operationsPerSecTime = 0;
        for (int i = 0; i < REPETITION_NUMBER; i++) {
            long startTime = System.nanoTime();
            function.apply(implementation);
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
            operationsPerSecTime += 1.0*THREAD_POOL_SIZE*NUMBER_OF_ITERATIONS/(endTime - startTime);
        }
        printWriter.println(implementation.getShortName() + " total time: " + totalTime/REPETITION_NUMBER);
        printWriter.println(implementation.getShortName() + " operations in ns: " +  String.format("%.4f", operationsPerSecTime/REPETITION_NUMBER));
    }

    public void performPushAndPop(IStack implementation) throws InterruptedException {

        executors = newFixedThreadPool(THREAD_POOL_SIZE);
        executors.execute(() -> {
            for (int j = 0; j < NUMBER_OF_ITERATIONS; j++) {
                Random rand = new Random();
                int number = rand.nextInt(100);
                implementation.push(number);
                try {
                    implementation.pop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executors.shutdown();
        executors.awaitTermination(MAX_VALUE, DAYS);
    }

    public void performPush(IStack implementation) throws InterruptedException {

        executors = newFixedThreadPool(THREAD_POOL_SIZE);
        executors.execute(() -> {
            for (int j = 0; j < NUMBER_OF_ITERATIONS; j++) {
                Random rand = new Random();
                int number = rand.nextInt(100);
                implementation.push(number);
            }
        });
        executors.shutdown();
        executors.awaitTermination(MAX_VALUE, DAYS);
    }
}

