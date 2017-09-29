package testfileclock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giacomo.ravagnan
 */
public class FileClock extends Thread {

    Thread[] threads;
    int position;
    Thread interrompi;

    Random r = new Random();

    /**
     *
     * @param threads array where the thread is placed
     * @param position position of the thread in the array
     */
    public FileClock(Thread[] threads, int position) {
        this.threads = threads;
        this.position = position;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("here we have Thread n° " + position);
            try {
                TimeUnit.SECONDS.sleep(1);  //every thread sleep for 1 sec
                this.interrompi = this.threads[new Random().nextInt(threads.length)]; //pick a random thread
            } catch (InterruptedException ex) {
                System.out.println(this.position + " has been interrupted");
                threads = deleteThread(threads);    //another array of lenghth=length-1
                return;
            }
            Stop();  //stop the last thread remain     
        }
    }

    /**
     *
     * @param threads array of thread
     * @return se same array without an element
     */
    private Thread[] deleteThread(Thread[] threads) {
        Thread[] sostitution = new Thread[threads.length - 1];
        for (int i = 0; i < sostitution.length; i++) {
            if (threads[i] != Thread.currentThread()) {
                sostitution[i] = threads[i];
            }
        }
        return sostitution;

    }

    /**
     * check is all thread are stopped
     *
     * @return a boolean :)
     */
    private boolean lastAlive() {
        for (int i = 0; i < this.threads.length; i++) {
            if (this.threads[i].isAlive() && this.threads[i] != Thread.currentThread()) {
                return false;
            }
        }
        return true;
    }

    /**
     * it is used to stop the last thread
     */
    private void Stop() {
        if (lastAlive()) {
            System.out.println("last one " + this.position);
            Thread.currentThread().interrupt();
        } else {
            this.interrompi.interrupt();
        }
    }

}
