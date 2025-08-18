// Using Thread class just to understand threads and call stacks. Use ExecutorService in actual development.
// Careful with concorrency issues, we dont know which thread will complete first ( test with sleep())

import java.util.concurrent.TimeUnit;

class MySlowRunnable implements Runnable {
    public void run() { // implemented from Runnable
        go();
    }
    // this is only here so we can fill the call stack with multiple methods
    public void go() {

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        doMore();
    }

    public void doMore() {
        System.out.println(Thread.currentThread().getName() + ": top of the stack ");

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread.dumpStack();
    }
}

class Threads {
    public static void main(String[] args) {

        // The following code will not create a NEW call stack since the run() method was called directly from inside the main() method
        // so it is part of the stack of the main thread ( main() -> run() -> go() -> doMore() )
        /*
        MyRunnable runnable = new MyRunnable();
        runnable.run();
        System.out.println(Thread.currentThread().getName() + ": never left main");
        Thread.dumpStack();
        */

        // instead we need to pass the Runnable instance to the new Thread constructor
        Runnable threadJob = new MySlowRunnable();
        Thread myThread = new Thread(threadJob);
        // need to start it 
        
        // this thread will take at least 2 seconds to complete due to the sleep() calls
        myThread.start();

        System.out.println(Thread.currentThread().getName() + ": back in main");
        
        try {
            TimeUnit.MILLISECONDS.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // this will show after 1.5 seconds
        Thread.dumpStack();
    }
}
