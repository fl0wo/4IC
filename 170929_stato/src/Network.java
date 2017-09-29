import java.util.Date;

public class Network extends Thread {
    private Thread td;

    Network(Thread td, String name){
        super(name);
        this.td = td;
    }

    @Override
    public void run() {
        System.out.printf("Network connected: %s\n", new Date());
        try {
            td.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Network not connected: %s\n", new Date());
    }

}
