package Threads;

import java.util.concurrent.BlockingQueue;

public class DivideTwoThread extends Thread {
    private final BlockingQueue<Boolean> queue;
    private final int availableQueueSize;

    public DivideTwoThread(BlockingQueue<Boolean> queue, int availableQueueSize) {
        this.queue = queue;
        this.availableQueueSize = availableQueueSize;
    }

    @Override
    public void run(){
        try{
            System.out.println("Thread DivideTwoThread is working!");
            for (int i = 0; i < availableQueueSize; i++){
                var verifyNumber = i + 1;
                boolean isVerify = verifyNumber % 2 == 0;
                queue.put(isVerify);
                System.out.println("Thread DivideTwoThread added result: " + isVerify + " for number " + verifyNumber + ". " + verifyNumber + "/2");
                Thread.sleep(2000);
            }
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
    }
}
