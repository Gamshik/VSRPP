import Threads.AddThread;
import Threads.MultiplyThread;
import Threads.ResultThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main thread started!");

        var queueLength = 12;

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueLength);

        try{
            var resultThread = new ResultThread(queue);

            resultThread.start();

            var multiplyThread = new MultiplyThread(queue, queueLength / 2);

            var addThread = new AddThread(queue, queueLength / 2);

            multiplyThread.start();
            addThread.start();

            multiplyThread.join();
            addThread.join();

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
