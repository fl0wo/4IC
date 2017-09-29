package javathreadsclock;

/**
 *
 * @author giovanni.stevanato
 */

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FileClock implements Runnable {
    
    
    Thread[] threads;
    int position;
    Random r = new Random();
    Thread stop;
    
    public FileClock(Thread[] t, int position) {
        this.threads = t;
        this.position = position;
    }
    
    
    
    @Override
    public void run() {
        while(true) {
            System.out.println(""+this.position+" "+ new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
                this.stop = this.threads[new Random().nextInt(threads.length)];
            } catch (InterruptedException e) {
                System.out.println("The FileClock "+this.position+" has been interrupted");
                threads=deleteThread(threads);
                return;
            }
            stop();
        }
    }

    
    private void stop() {
        if(isLastAlive()){
            System.out.println("Im the last alive "+this.position);
            Thread.currentThread().interrupt();
        }
        else{
            this.stop.interrupt();
        }
    }
    
    
    private boolean isLastAlive() {
        for (int i = 0; i < this.threads.length; i++) {
            if(this.threads[i].isAlive() && this.threads[i]!=Thread.currentThread()){
                return false;
            }
        }
        return true;
    }
    
    
    private Thread[] deleteThread(Thread[] threads) {
        Thread[] t = new Thread[threads.length-1];
        for (int i = 0; i < t.length; i++) {
            if(threads[i]!= Thread.currentThread()){
                t[i] = threads[i];
            }
            else{
                System.out.println("Find Myself: "+this.position);
            }
        }
        return t;
    }
}