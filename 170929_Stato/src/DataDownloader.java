import java.util.Date;
import java.util.concurrent.TimeUnit;

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