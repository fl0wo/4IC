/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasksemaforo;

/**
 *
 * @author Florian
 */
public class Simul2 {

    public static void main(String args[]) {
        Risorsa[] risorse = new Risorsa[]{new Risorsa("Valore1"), new Risorsa("Valore2")};
        
        Task task1 = new Task(risorse,"Task1");
        Task task2 = new Task(risorse,"Task2");
        
        Task[] array = new Task[]{task1,task2};
        
        Scheduler s = new Scheduler(array);
        
        Thread scheduler = new Thread(s);

        scheduler.start();
    }
}
