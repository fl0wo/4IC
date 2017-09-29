import static java.lang.Thread.sleep;

public class TestDataDownload {
    public static void main(String[] args) {
        DataDownloader ddl = new DataDownloader();
        Thread td = new Thread(ddl,"DataDownloaderThread");
        Thread tn = new NetworkConnection(td,"NetworkConnectionThread");
        new Thread(new Observer(tn)).start();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        tn.start();
        td.start();
        return;
    }
}