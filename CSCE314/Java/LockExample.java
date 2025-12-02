import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    static List<Integer> important = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    static Lock importantChange = new ReentrantLock();

    public static void main(String[] args){
        Thread t1 = new Thread(() -> {
            System.out.println("T1: Trying to acquire lock...");
            importantChange.lock();
            try {
                System.out.println("T1: Lock acquired.");
                System.out.println("T1: Adding 1 to list...");
                important.add(1);
            } finally {
                System.out.println("T1: Unlocking...");
                importantChange.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("T2: Trying to acquire lock...");
            importantChange.lock();
            try {
                System.out.println("T2: Lock acquired.");
                System.out.println("T2: Adding 2 to list...");
                important.add(2);
            } finally {
                System.out.println("T2: Unlocking...");
                importantChange.unlock();
            }
        });

        t1.start();
        t2.start();

        // Wait for both to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ignored) {}

        System.out.println("Final list: " + important);
    }
}
