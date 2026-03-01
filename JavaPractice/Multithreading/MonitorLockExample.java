public class MonitorLockExample {
    
    public synchronized void task1() {

        // Do Something
        try {
            System.out.println("Inside task1..");
            System.out.println("Task1 Thread Name: " + Thread.currentThread().getName());
            
            Thread.sleep(10000);
            System.out.println("Task1 2nd Thread Name: " + Thread.currentThread().getName());
            System.out.println("Task1 is completed..");
        }
        catch(Exception e) {
            System.out.println("Task1 Exception is handled..");
        }
    }

    public void task2() {

        System.out.println("In task2, before synchronized..");
        System.out.println("Task2 before synchronized Thread Name: " + Thread.currentThread().getName());

        synchronized (this) {
            System.out.println("In task2, inside synchronized....");
            System.out.println("Task2 after synchronized Thread Name: " + Thread.currentThread().getName());
        }
    }

    public void task3() {

        System.out.println("Inside task3..");
        System.out.println("Task3 Thread Name: " + Thread.currentThread().getName());
    }
}
