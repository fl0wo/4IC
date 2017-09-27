/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provafileclock;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.Random;
/**
 *
 * @author Marco
 */
public class FileClock implements Runnable {
    private Thread[] threads;
    private int pos;
    
    public FileClock(Thread[] threads, int pos){
        this.threads=threads;
        this.pos=pos;
    }
    
    @Override
    public void run() {
        while(true){
            System.out.println("Posizione:"+" "+pos+"; "+new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Il thread in posizione "+pos+" è stato interrotto");
                break;
            }
            if(sopravissuto()){
                System.out.println("Il thread in posizione numero "+pos+" è l'unico soperstite");
                Thread.currentThread().interrupt();
            } else
                threads[new Random().nextInt(threads.length)].interrupt();
        }
    }
    
    private boolean sopravissuto(){
        for(int i=0; i<threads.length; i++){
            if(threads[i].isAlive()&&threads[i]!=Thread.currentThread())
                return false;
        }
        return true;
    }
}
