package testdatadownload;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class TestDataDownload{
    public static void main(String[] args){
        DataDownloader ddl = new DataDownloader();
        Thread td = new Thread(ddl, "DataDownloaderThread");
	Thread tn = new NetworkConnection(td, "NetworkConnectionThread");
	System.out.println(tn.getName()+": "+tn.getState());    //NEW
	System.out.println(td.getName()+": "+td.getState());
	tn.start();
	td.start();
	System.out.println(tn.getName()+": "+tn.getState());    //RUNNABLE
	System.out.println(td.getName()+": "+td.getState());
        try{
            TimeUnit.SECONDS.sleep(3);
	}catch(InterruptedException e){
            e.printStackTrace();
	}
        System.out.println(tn.getName()+": "+tn.getState());    //TERMINATED
	System.out.println(td.getName()+": "+td.getState());
    }
}
class DataDownloader implements Runnable{
    @Override
    public void run(){
	System.out.printf("Connecting: %s\n", new Date());
	try{
            TimeUnit.SECONDS.sleep(2);
	}catch(InterruptedException e){
            e.printStackTrace();
	}
	System.out.printf("Download finished: %s\n", new Date());
    }
}
class NetworkConnection extends Thread{
    private Thread td;
    NetworkConnection(Thread td, String name){
        super(name);
	this.td = td;
    }
    @Override
    public void run(){
        System.out.println(td.getName()+": "+td.getState());
        System.out.printf("Network connected: %s\n", new Date());
        System.out.println(td.getName()+": "+td.getState());    //BLOCKED
	System.out.println(Thread.currentThread().getName()+": "+Thread.currentThread().getState());
	try{
            Thread helper1 = new Thread(new Helper(Thread.currentThread(), Thread.currentThread().getName()));
            helper1.start();    //WAITING
            System.out.println(td.getName()+": "+td.getState());
            System.out.println(Thread.currentThread().getName()+": "+Thread.currentThread().getState());
            Thread helper2 = new Thread(new Helper(td, td.getName()));
            helper2.start();    //TIMED_WAITING
            td.join();
	}catch(InterruptedException e){
            e.printStackTrace();
	}
	System.out.printf("Network not connected: %s\n", new Date());
    }
}