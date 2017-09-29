import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestDataDownload {

    public static void main(String[] args) {
        DataDownloader ddl = new DataDownloader();
        Thread td = new Thread(ddl, "DataDownloaderThread");
        Thread tn = new NetworkConnection(td, "NetworkConnectionThread");
        tn.start();
        td.start();
    }
}

class DataDownloader implements Runnable {

    @Override
    public void run() {
        System.out.printf("Connessione: %s\n",new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Download finito: %s\n",new Date());
    }
}

class NetworkConnection extends Thread {

    private Thread td; 

    NetworkConnection(Thread td, String name) {
        super(name);
        this.td = td;
    }

    @Override
    public void run() {
        System.out.printf("Network connessa: %s\n", new Date());
        try {
            Thread osservatore = new Thread(new Osserva(Thread.currentThread()));
            osservatore.start();    
            td.join();  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Network non connessa: %s\n",new Date());
    }

    class Osserva implements Runnable {

        private Thread Observable;

        private Osserva(Thread currentThread) {
            this.Observable = currentThread;
        }

        @Override
        public void run() {

            if (Observable.isAlive()) {    
                System.out.println(Observable.getState().name());
            }
            while(Observable.getState()!=Thread.State.BLOCKED){

            }
             System.out.println(Observable.getState().name());
        }

    }
}