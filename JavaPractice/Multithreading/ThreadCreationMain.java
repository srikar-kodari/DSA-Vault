public class ThreadCreationMain {
    public static void main(String[] args) {
        
        // Thread Creation by implementing Runnable interface
        System.out.println("Going inside Main method: " + Thread.currentThread().getName());
        ThreadCreation1 runnableObj = new ThreadCreation1();
        Thread thread = new Thread(runnableObj);    // Thread is created
        thread.start();
        System.out.println("Finish Main method: " + Thread.currentThread().getName());


        // // Thread Creation by extending Thread Class
        // System.out.println("Going inside Main method: " + Thread.currentThread().getName());
        // ThreadCreation2 myThread = new ThreadCreation2();
        // myThread.start();
        // System.out.println("Finish Main method: " + Thread.currentThread().getName());

    }
}
