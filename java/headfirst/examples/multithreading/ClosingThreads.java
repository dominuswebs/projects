package multithreading;

/*
 * Using javac go to the root of the multithreading folder (examples)
 * Then run
 * javac multithreading/ClosingThreads.java
 * java multithreading.ClosingThreads
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClosingThreads {
    public static void main(String[] args) {
        try (ExecutorService threadPool = Executors.newFixedThreadPool(2)) {
            threadPool.execute(new LongJob("Long Job"));
            threadPool.execute(new ShortJob("Short Job"));
            /*
             * Ask the ExecutorService  to shut down. If you call  execute() with a job  after this, you will get a  RejectedExecutionException.
             * The ExecutorService will  continue to run all the jobs  that are running, and run any  waiting jobs t
             */
            threadPool.shutdown();
            /*
             * Wait up to 5 seconds for the ExecutorService to finish everything.
             * If this method hits the timeout before everything has finished, it returns false.
             * We can manipulate this by setting the LongJob sleep to be over 5 seconds
             */
            try {
                boolean finished = threadPool.awaitTermination(5, TimeUnit.SECONDS);
                System.out.println("Finished? " + finished);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadPool.shutdownNow();
        } catch ( IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}

class ShortJob implements Runnable {

    private String message;

    public void run() { // implemented from Runnable
        System.out.println(message + " completed");
    }

    ShortJob(String s) {
        message = s;
    }
}

class LongJob implements Runnable {

    private String message;

    public void run() { // implemented from Runnable

        System.out.println("Starting long job");
        // if the sleep is longer than awaitTermination value we will get an Interrupted Exception because sleep never completes
        try {
            TimeUnit.SECONDS.sleep(6);
            System.out.println(message + " completed");
        } catch (InterruptedException e) {
            System.out.println("You interrupted my sleep");
            e.printStackTrace();
        }
    }

    LongJob(String s) {
        message = s;
    }
}
