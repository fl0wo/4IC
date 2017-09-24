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
                threads=eliminaThread(threads);
                return;
            }
            interrompi();
        }
    }

    
    private void interrompi() {
        if(sonoLunicoThreadInVita()){
            System.out.println("Im the last alive "+this.posizione);
            Thread.currentThread().interrupt();
        }
        else{
            this.interrompi.interrupt();
        }
    }
    
    
    private boolean sonoLunicoThreadInVita() {
        for (int i = 0; i < this.threads.length; i++) {
            if(this.threads[i].isAlive() && this.threads[i]!=Thread.currentThread()){
                return false;
            }
        }
        return true;
    }
    
    
    private Thread[] eliminaThread(Thread[] threads) {
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
