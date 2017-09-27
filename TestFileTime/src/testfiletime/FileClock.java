package testfiletime;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FileClock implements Runnable {
    
    private Thread[] array;
    private int numero;
    private Random r = new Random();
    
    public FileClock(Thread[] array, int numero) {
        this.array = array;
        this.numero = numero;
    }

    @Override
    public void run() {
        
        int random = r.nextInt(10);
        
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s\n", new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("I, "+this.numero+" have been itnerrupted");
                break;// cosi esche dal for e di conseguenza finisce il metodo run
                //oppure return; cosi da uccidere il thread e concludere il metodo run 
            }
            
            if(i==random){
                interrompiUnThread();
                random = i + r.nextInt(10-i);
            }
            if(sonoUltimo()){
                System.out.println("I , "+this.numero+" am the last thread alive , i decide to commit a suicide");
                return;
            }
        }
    }

    private void interrompiUnThread() {
        int posizioneThread = prendiPosizione();
        Thread daInterrompere = this.array[posizioneThread];
        
        if(daInterrompere!=null){
            if(daInterrompere.isAlive()){
                this.array[posizioneThread].interrupt();
                System.out.println("I "+this.numero+" have interrupted the Thread number "+posizioneThread);
            }
        }
    }

    private int prendiPosizione() {
        // pos cas da 0 a thread.lenght ma != this.posizione
        
        int caso = r.nextInt(this.array.length);
        while(caso == this.numero){
            caso=r.nextInt(this.array.length);
        }
        return caso;
    }

    private boolean sonoUltimo() {
        for (int i = 0; i < this.array.length; i++) {
            if(this.array[i].isAlive() && this.array[i]!= Thread.currentThread()){
                return false;
            }
        }
        return true;
    }
}
