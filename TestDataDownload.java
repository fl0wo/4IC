package statithread;

/**
 *
 * @author Florian
 */
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
        System.out.printf("Connecting: %s\n",new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Download finished: %s\n",new Date());
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
            Thread osservatore = new Thread(new Osserva(Thread.currentThread()));
            osservatore.start();    // Metto il thread osservatore in ready
            td.join();  // e solo dopo chiamo il metodo join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Network not connected: %s\n",new Date());
    }

    class Osserva implements Runnable {

        private Thread daOsservare;

        private Osserva(Thread currentThread) {
            this.daOsservare = currentThread;
        }

        @Override
        public void run() {
            
            if (daOsservare.isAlive()) {    // Se il thread é attivo
                // Stampo lo stato che ha durante il join
                System.out.println(daOsservare.getState().name());
            }
                /*
                /////////////IPOTESI MIA////////////////
                Sono piuttosto convinto del fatto che un Thread prima di tornare RUNNABLE
                debba spostarsi in uno stato chiamato "BLOCKED" nella quale il thread é inattivo,
                e ci va se e solo se sta provando ad accedere ad una risorsa (Object) che é
                gia utilizzata da un altro Thread , che quindi attenderemo che torni disponibile
                , tutte queste decisioni sono ovviamente prese dalla scheduler...
                */
                
            // Sto nel while fino a quando il thread che era in WAITING non va in BLOCKED
            // Se la mia teoria fosse sbagliata non uscirebbe mai dal while
            while(daOsservare.getState()!=Thread.State.BLOCKED){
                
            }
            // E invece esce e stampa subito RUNNABLE quindi prima di passara a RUNNABLE
            // c'é un istante in cui é BLOCKED.
            System.out.println(daOsservare.getState().name());
        }

    }
}
