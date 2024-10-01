import Threads.DivideFiveThread;
import Threads.DivideTwoThread;
import Threads.HandleResultThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main thread started!");

        var queueLength = 10;

        BlockingQueue<Boolean> queue = new ArrayBlockingQueue<Boolean>(queueLength);

        try{
            var resultThread = new HandleResultThread(queue);

            resultThread.start();

            var divideTwoThread = new DivideTwoThread(queue, queueLength / 2);

            var divideFiveThread = new DivideFiveThread(queue, queueLength / 2);

            divideTwoThread.start();
            divideFiveThread.start();

            divideTwoThread.join();
            divideFiveThread.join();

            resultThread.interrupt();
            resultThread.join();
        }
        catch(InterruptedException e){
            System.out.print("Process has been interrupted");
        }

        System.out.println("Main thread finished!");

        System.out.println("Queue result: " + queue);
    }
}
