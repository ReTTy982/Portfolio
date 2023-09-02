package edu.pwr.processors;

import edu.pwr.processing.Processor;
import edu.pwr.processing.Status;
import edu.pwr.processing.StatusListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LowerCaseProc implements  Processor{
    public static int taskID=0;
    private String result = null;
    private boolean transfer = true;
    @Override
    public boolean submitTask(String task, StatusListener sl) {
        taskID++;
        AtomicInteger ai = new AtomicInteger(0);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();


        executorService.scheduleAtFixedRate(
                () -> {
                    ai.incrementAndGet();
                    sl.statusChanged(new Status(taskID, ai.get()));

                },
                1, 10, TimeUnit.MILLISECONDS);


        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ai.get() >= 100) {

                    result = task.toLowerCase();
                    executorService.shutdown();
                    executor.shutdown();
                    return true;

                }
            }
        });

        return false;
    }

    @Override
    public String getInfo() {
        return "To lowercase";
    }

    @Override
    public String getResult() {
        return result;
    }
}
