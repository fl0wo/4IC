import java.util.Date;

class NetworkConnection extends Thread {

    private Thread td; // the downloader

    NetworkConnection(Thread td, String name){
        super(name);
        this.td = td;
    }

    @Override
    public void run() {
        System.out.printf("Network connected: %s\n",
                new Date());
        try {
            td.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Network not connected: %s\n",
                new Date());
    }
}