package ru.sbt.mp.stack.tests;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mp.stack.implementations.IStack;

import java.util.Random;
import java.util.concurrent.ExecutorService;

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
    private final long REPETITION_NUMBER = 10;


    @Before
    public void prepare() {
        factory = new ImplementationsFactory();
    }

    @Test
    public void testStack() {
        for (IStack implementation : factory.getAllImplementations()) {
            measureTime(implementation);
        }
    }

    public void measureTime(IStack implementation) {
        long startTime = System.nanoTime();

        try {
            for (int i = 0; i < REPETITION_NUMBER; i++) {
                performCalculations(implementation);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();

        System.out.println(implementation.getShortName() + ": " +  (endTime - startTime)/REPETITION_NUMBER);
    }

    public void performCalculations(IStack implementation) throws InterruptedException {

        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
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
    }
}
