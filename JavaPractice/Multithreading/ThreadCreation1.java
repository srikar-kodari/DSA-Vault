public class ThreadCreation1 implements Runnable {

    @Override
    public void run() {

        System.out.println("Code executed by thread: " + Thread.currentThread().getName());
    }
    
}
