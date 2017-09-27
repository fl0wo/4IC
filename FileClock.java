package testfiletime;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FileClock implements Runnable {
    
    
    Thread[] threads;
    int posizione;
    Random r = new Random();
    Thread interrompi;
    
    public FileClock(Thread[] threads, int posizione) {
        this.threads = threads;
        this.posizione = posizione;
    }

    
    
    
    @Override
    public void run() {
        while(true) {
            System.out.println(""+this.posizione+" "+ new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
                this.interrompi = this.threads[new Random().nextInt(threads.length)];
            } catch (InterruptedException e) {
                System.out.println("The FileClock "+this.posizione+" has been interrupted");
                threads=deleteThread(threads);
                return;
            }
            stop();
        }
    }

    
    private void stop() {
        if(isLastThreadAlive()){
            System.out.println("Im the last alive "+this.posizione);
            Thread.currentThread().interrupt();
        }
        else{
            this.interrompi.interrupt();
        }
    }
    
    
    private boolean isLastThreadAlive() {
        for (Thread thread : this.threads) {
            if (thread.isAlive() && thread != Thread.currentThread()) {
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
                System.out.println("Find Myself: "+this.posizione);
            }
        }
        return t;
    }
}
