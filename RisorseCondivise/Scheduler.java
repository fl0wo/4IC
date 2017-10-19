package tasksemaforo;
/**
 *
 * @author Florian
 */
public class Scheduler implements Runnable{
    
    Task[] arrayTask;
    public Scheduler(Task[] arrayTask) {
        this.arrayTask = arrayTask;
    }

    @Override
    public void run() {
        for (Task arrayTask1 : arrayTask) {
            new Thread(arrayTask1::job).start();    // per ogni task avvio il job con un altro thread <3
        }
    }
}
