import java.util.concurrent.*;

public class ExecutorsTester {
    
    public static void main(String[] args) {
        Runnable job = new MyRunnable();
    
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(job);

        System.out.println(Thread.currentThread().getName() + ": back in main");
        Thread.dumpStack();
        // need to shutdown or it will keep waiting for new jobs
        executor.shutdown();
    }
}
