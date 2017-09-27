/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provafileclock;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Marco
 */
public class ProvaFileClock {
    public static void main(String[] args) {
        Thread[] threads=new Thread[10];
        for(int i=0; i<threads.length; i++){
            threads[i]=new Thread(new FileClock(threads, i));
            threads[i].start();
        }
    }
}
