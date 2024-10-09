package Threads;

import java.util.concurrent.BlockingQueue;

public class MultiplyThread extends Thread {
    private final BlockingQueue<Integer> queue;
    private final int availableQueueSize;

    public MultiplyThread(BlockingQueue<Integer> queue, int availableQueueSize) {
        this.queue = queue;
        this.availableQueueSize = availableQueueSize;
    }

    public synchronized void run(){
        try{
            System.out.println("Thread MultiplyThread is working!");
            for (int i = 0; i < availableQueueSize; i++){
                var firstNumber = i + 1;
                var secondNumber = firstNumber + 10;

                var result = firstNumber * secondNumber;

                queue.put(result);

                System.out.println("Thread MultiplyThread added result: " + result + " for action " + firstNumber + " * " + secondNumber);

                Thread.sleep(4000);
            }
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
    }
}
