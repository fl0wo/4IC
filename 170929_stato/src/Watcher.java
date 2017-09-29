import java.util.concurrent.TimeUnit;

/**
 * Class that watches for a Thread's state change and prints it out
 */
public class Watcher extends Thread{
    private Thread t1, t2;

    /**
     * Standard constructor with the two Threads to monitor
     * @param t1 The Thread 1 to monitor
     * @param t2 The Thread 1 to monitor
     */
    public Watcher(Thread t1, Thread t2){
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public void run(){
        State s1 = t1.getState();
        State s2 = t2.getState();
        System.out.println("\t" + t1.getName() + " is " + s1);
        System.out.println("\t" + t2.getName() + " is " + s2);

        for(;;){
            if(!t1.getState().equals(s1)) {
                s1 = t1.getState();
                System.out.println("\t" + t1.getName() + " is " + s1);
            }
            if(!t2.getState().equals(s2)){
                s2 = t2.getState();
                System.out.println("\t" + t2.getName() + " is " + s2);
            }
            try{
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("\tI'm no more needed");
                break;
            }
        }
    }
}
