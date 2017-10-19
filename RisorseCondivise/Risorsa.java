/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasksemaforo;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Florian
 */
public class Risorsa {

    public Semaphore semaphore;
    public Object value;
    
    public Risorsa(int numero) {
        semaphore = new Semaphore(1, true);
    }

    public Risorsa(Object value) {
        this.value = value;
        semaphore = new Semaphore(1, true);
    }

    public Risorsa(Semaphore semaphore, Object value) {
        this.semaphore = semaphore;
        this.value = value;
    }
    

}
