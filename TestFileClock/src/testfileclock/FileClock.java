package testfileclock;

import java.util.*;
import java.util.concurrent.*;

public class FileClock implements Runnable {
    
    Thread[] threads;
    int pos;
    
    public FileClock(Thread[] threads, int pos) {
        this.threads = threads;
        this.pos = pos;
    }
    
    @Override
    public void run() {
        while(true) {
            int time = 2;
            System.out.println(pos+"\t"+ new Date());
            try {
                TimeUnit.SECONDS.sleep(time);
                if(isLastOne())
                    threads[(int)(Math.random()*threads.length)].interrupt();
                else
                    Thread.currentThread().interrupt();
            } catch (InterruptedException e) {
                System.out.println("The FileClock "+pos+" has been interrupted");
                threads = deleteThread(threads);
                return;
            }
        }
    }
    
    private boolean isLastOne() {
        for (Thread thread : threads)
            if(thread != Thread.currentThread() && thread.isAlive()) 
                return false;
        
        return true;
    }
    
    private Thread[] deleteThread(Thread[] threads) {
        Thread[] t = new Thread[threads.length-1];
        for (int i=0; i<pos; i++) {
            t[i] = threads[i];
        }
        for (int i=pos+1; i<threads.length; i++) {
            t[i-1] = threads[i];
        }
        return t;
    }
}