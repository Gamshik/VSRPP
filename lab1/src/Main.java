import Threads.DivideFiveThread;
import Threads.DivideTwoThread;
import Threads.HandleResultThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main thread started!");

        int queueLength = 6;

        BlockingQueue<Boolean> queue = new ArrayBlockingQueue<Boolean>(queueLength);

        try{
            for (int i = 0; i < queueLength / 2; i++) {
                var divideTwoThread = new DivideTwoThread(i + 1, queue);

                var divideFiveThread = new DivideFiveThread(i + 1, queue);
                divideTwoThread.start();
                divideFiveThread.start();

                divideTwoThread.join();
                divideFiveThread.join();
            }

            var resultThread = new HandleResultThread(queue, queueLength);

            resultThread.start();
            resultThread.join();
        }
        catch(InterruptedException e){

            System.out.print("Process has been interrupted");
        }

        System.out.println("Main thread finished!");
    }
}
