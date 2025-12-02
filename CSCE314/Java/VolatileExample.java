public class VolatileExample {

    // This variable is shared between threads
    // volatile ensures visibility of updates across threads
    private volatile boolean running = true;

    public void startExample() {

        // Thread 1 — Worker thread
        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1: Worker started.");

            // This loop will continue running until
            // another thread sets 'running = false'
            while (running) {
                System.out.println("working...");
            }

            System.out.println("Thread 1: Worker stopped.");
        });

        thread1.start();

        // Give Thread 1 some time to run
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        // Thread 2 — Controller thread
        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2: Requesting stop.");
            running = false;   // <-- volatile write
        });

        thread2.start();
    }

    public static void main(String[] args) {
        new VolatileExample().startExample();
    }
}
