package Threads;
import java.util.concurrent.BlockingQueue;

public class HandleResultThread extends Thread {
    private final BlockingQueue<Boolean> queue;

    public HandleResultThread(BlockingQueue<Boolean> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread HandleResultThread is working!");

            while (!isInterrupted()) {
                for (Boolean value : queue) {
                    System.out.println("Has been got result: " + value);
                    if (!queue.remove(value)) throw new Exception("Queue item is not deleted!!!");
                }
            }

            System.out.println("Thread HandleResultThread is finished!");

        } catch (InterruptedException e) {
            System.out.println("Thread HandleResultThread has been interrupted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
