package Threads;
import java.util.concurrent.BlockingQueue;

public class DivideFiveThread extends Thread {
    private final BlockingQueue<Boolean> queue;
    private final int verifyNumber;

    public DivideFiveThread(int verifyNumber, BlockingQueue<Boolean> queue) {
        this.verifyNumber = verifyNumber;
        this.queue = queue;
    }

    @Override
    public void run(){
        try{
            System.out.println("Thread DivideFiveThread is working!");
            boolean isVerify = verifyNumber % 5 == 0;
            queue.put(isVerify);
            System.out.println("Thread DivideFiveThread added result: " + isVerify + " for number " + verifyNumber);
            Thread.sleep(2000);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
    }
}
