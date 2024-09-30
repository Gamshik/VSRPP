package Threads;

import java.util.concurrent.BlockingQueue;

public class HandleResultThread extends Thread {
    private final BlockingQueue<Boolean> queue;
    private final int totalResults;

    public HandleResultThread(BlockingQueue<Boolean> queue, int totalResults) {
        this.queue = queue;
        this.totalResults = totalResults;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread HandleResultThread is working!");

            // Collect all results from the queue
            for (int i = 0; i < totalResults; i++) {
                boolean result = queue.take();
                System.out.println("Thread HandleResultThread got result: " + result);
            }

        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
        }
    }
}
