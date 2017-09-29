import java.util.concurrent.TimeUnit;

public class TestDataDownload {
    public static void main(String[] args) {
        DataDownloader ddl = new DataDownloader();
        Thread td = new Thread(ddl,"DataDownloaderThread");
        Thread tn = new Network(td,"NetworkConnectionThread");
        Thread watch = new Watcher(td, tn);     //thread to watch the state change
        watch.start();
        tn.start();
        td.start();
        try {
            TimeUnit.SECONDS.sleep(6);  //wait for all the threads to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        watch.interrupt();  //stops the thread watch to end the program
    }
}
