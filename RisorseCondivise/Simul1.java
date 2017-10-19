package tasksemaforo;

/**
 *
 * @author Florian
 */
public class Simul1 {

    public static void main(String args[]) {
        Risorsa risorsa = new Risorsa("Valore");
        
        Task task1 = new Task(risorsa,"Task1");
        Task task2 = new Task(risorsa,"Task2");
        
        Task[] array = new Task[]{task1,task2};
        
        Scheduler s = new Scheduler(array);
        
        Thread scheduler = new Thread(s);

        scheduler.start();
    }
}
