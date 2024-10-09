package Threads;
import java.util.concurrent.BlockingQueue;

public class ResultThread extends Thread {
    private final BlockingQueue<Integer> queue;

    public ResultThread(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public synchronized void run() {
        try {
            System.out.println("Thread ResultThread is working!");

            while (!isInterrupted()) {
                for (Integer value : queue) {
                    System.out.println("Has been got result: " + value);
                    if (!queue.remove(value)) throw new Exception("Queue item is not deleted!");
                }
            }

            System.out.println("Thread ResultThread is finished!");

        } catch (InterruptedException e) {
            System.out.println("Thread ResultThread has been interrupted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
