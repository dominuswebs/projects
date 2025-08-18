import java.util.concurrent.*;

public class SynchronizerCountDownLatch {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        // wait for 2 countdown events to complete
        CountDownLatch latch = new CountDownLatch(2);

        executor.execute(() -> waitForLatchThenPrint(latch));

        System.out.println("back in main");
        // tell the latch to countdown
        // reduce the value by 1. Value is now 1
        latch.countDown();
        // shutdown the service so the program doesn't stay idle waiting for more jobs
        executor.shutdown();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("timeout complete");
        // reduce the value by 1. Value is now 0. The thread with await can now run.
        latch.countDown();
    }

    private static void waitForLatchThenPrint(CountDownLatch latch) {
        try {
            // wait until countdown is complete. Until then this thread stays in a non-runnable state.
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("top of the stack");
    }
}
