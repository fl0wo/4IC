
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestDataDownload {

    public static void main(String[] args) {
        DataDownloader ddl = new DataDownloader();
        
        Thread td = new Thread(ddl, "DataDownloaderThread");
        Thread tn = new NetworkConnection(td, "NetworkConnectionThread");

        Watcher cambiamenti = new Watcher(td, tn);
        cambiamenti.start();
    }
}

////////////////////////////////////////////////////////////////////////////////

class DataDownloader implements Runnable {

    @Override
    public void run() {
        System.out.printf("Connecting: %s\n",
                new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Download finished: %s\n",
                new Date());
    }
}

////////////////////////////////////////////////////////////////////////////////

class NetworkConnection extends Thread {

    private Thread td;

    NetworkConnection(Thread td, String name) {
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

////////////////////////////////////////////////////////////////////////////////

class Watcher extends Thread {

    Thread data;
    Thread network;

    Watcher(Thread td, Thread tn) {
        this.data = td;
        this.network = tn;
    }

    public void toStringData() {
        System.out.println("Data Downloader Status :" + data.getState());
    }

    public void toStringNet() {
        System.out.println("Network Connection Status :" + network.getState());
    }
   
    @Override
    public void run() {
        this.toStringData();
        this.toStringNet();
        network.start();
        data.start();
        this.toStringData();
        this.toStringNet();
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.toStringData();
        this.toStringNet();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.toStringData();
        this.toStringNet();
    }

}
