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
    public FileClock(Thread[] threads, int position) {
        this.threads = threads;
        this.position = position;
    }

    @Override
    public void run() {
		while(true){
        System.out.println("here we have Thread n° "+position );
        try{
            TimeUnit.SECONDS.sleep(1);
             this.interrompi = this.threads[new Random().nextInt(threads.length)];
        } catch (InterruptedException ex) {
      System.out.println(this.position+" has been interrupted");
      threads=deleteThread(threads);
                return;
    }
       Stop();       
   }
	}
    private Thread[] deleteThread(Thread[] threads) {
        Thread[] sostitution = new Thread[threads.length-1];
        for (int i = 0; i < sostitution.length; i++) {
            if(threads[i]!= Thread.currentThread()){
                sostitution[i] = threads[i];
            }
        }
        return sostitution;

    }
	 private void Stop() {
        if(lastAlive()){
            System.out.println("last one "+this.position);
            Thread.currentThread().interrupt();
        }
        else{
            this.interrompi.interrupt();
        }
    }
    
    
    private boolean lastAlive() {
        for (int i = 0; i < this.threads.length; i++) {
            if(this.threads[i].isAlive() && this.threads[i]!=Thread.currentThread()){
                return false;
            }
        }
        return true;
}
        }

    


    

