import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestDataDownload {
    public static void main(String[] args) {
        DataDownloader ddl = new DataDownloader();
        Thread td = new Thread(ddl,"DataDownloaderThread");
        Thread tn = new NetworkConnection(td,"NetworkConnectionThread");
        Thread tt = new Terminated(td, tn);
        tn.start();tt.start();
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

class NetworkConnection extends Thread {
    
    private Thread td; // the downloadr
    
    NetworkConnection(Thread td, String name){
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

class Terminated extends Thread{
	private Thread t1, t2;
	
	Terminated(Thread td, Thread tn){
		this.t1=td;
		this.t2=tn;
	}
	
	@Override
	public void run (){
		System.out.println("Thread 1 start ");
		System.out.println("Thread 2 start ");
		try{
			TimeUnit.SECONDS.sleep(1);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Threads 1 stopped ");
		System.out.println("Threads 2 stopped ");
	}
}
