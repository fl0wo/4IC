package testfiletime;

/**
 *
 * @author Florian
 */
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestDataDownload {

    public static void main(String[] args) throws InterruptedException {
        DataDownloader ddl = new DataDownloader();
        Thread td = new Thread(ddl, "DataDownloaderThread");
        Thread tn = new NetworkConnection(td, "NetworkConnectionThread");
        Osserva osservatore;

        osservatore = new Osserva(tn);
        Thread osservato = new Thread(osservatore, "TD");
        osservato.start();    // Metto il thread osservatore in ready
        Thread.sleep(10);
        tn.start();
        td.start();
    }
}

class DataDownloader implements Runnable {

    @Override
    public void run() {
        System.out.printf("Connecting: %s\n", new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Download finished: %s\n", new Date());
    }
}

class Osserva implements Runnable, Osservatore {

    private Thread daOsservare;

    Osserva(Thread currentThread) {
        this.daOsservare = currentThread;
    }

    @Override
    public void run() {
        
        aggiorna();//NEW 
        
        while (!daOsservare.getState().name().equals("TERMINATED")) {
            String statoPrecedente = daOsservare.getState().name();

            while (statoPrecedente.equals(daOsservare.getState().name())) {
            }
            aggiorna(); // CAMBIAMENTO STATO
        }
        
        aggiorna(); // TERMINATED
    }

    @Override
    public void aggiorna() {
        System.out.println("Lo stato del thread " + daOsservare.getName() + " é : " + daOsservare.getState().name());
    }

    @Override
    public void setDaOsservare(Osservato o) {
        this.daOsservare = (Thread) o;
    }

}

class NetworkConnection extends Thread {

    private Thread td; // the downloadr

    NetworkConnection(Thread td, String name) {
        super(name);
        this.td = td;
    }

    @Override
    public void run() {
        System.out.printf("Network connected: %s\n", new Date());
        try {
            // Per sapere in che stato si trova il thread dopo il comando join();
            // Uso un altro thread che chiamo osservatore dandogli come parametro
            // Il mio thread attuale il quale stato verra stampato il prima possibile

            td.join();  // e solo dopo chiamo il metodo join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Network not connected: %s\n", new Date());
    }

}
