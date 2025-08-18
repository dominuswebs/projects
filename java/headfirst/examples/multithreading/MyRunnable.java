public class MyRunnable implements Runnable {
    public void run() { // implemented from Runnable
        go();
    }
    // this is only here so we can fill the call stack with multiple methods
    public void go() {
        doMore();
    }

    public void doMore() {
        System.out.println(Thread.currentThread().getName() + ": top of the stack ");
        Thread.dumpStack();
    }
}