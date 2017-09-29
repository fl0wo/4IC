import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestDataDownload{
    public static void main(String[] args) {
        DataDownloader ddl = new DataDownloader();
        Blog stateReturn = new Blog();
        User look = new User();
        stateReturn.registerObserver(look);
        look.setObservable(stateReturn);
        Thread td = new Thread(ddl,"DataDownloaderThread");
        Thread tn = new NetworkConnection(td,"NetworkConnectionThread");
        Thread osservatore = new Osservatore(tn); 
        osservatore.start();            
        tn.start();
        look.getStatus(td);   //NEW state
        td.start();
        look.getStatus(td);  //RUNNABLE state
         try {
            TimeUnit.SECONDS.sleep(5); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
			osservatore.interrupt();
        
}
}

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


class NetworkConnection extends Thread {
    
    private Thread td; // the downloader
    
    NetworkConnection(Thread td, String name){
        super(name);
        this.td = td;
    }
    
    @Override
    public void run() {  
		//watcher TERMINATED state
		Blog stateReturn = new Blog();
        User look = new User();
        stateReturn.registerObserver(look);
        System.out.printf("Network connected: %s\n",
                           new Date());
         
        try {
            td.join();
        } catch (InterruptedException e) { 
            e.printStackTrace();
        }
        System.out.printf("Network not connected: %s\n", 
                           new Date()); 
                           look.getStatus(td);
    }
}

//it's used to get the WAITING state of the Thread
   class Osservatore extends Thread{
    private Thread thread;

    public Osservatore(Thread thread){
        this.thread = thread;
    }

      @Override
    public void run(){
        State stato = thread.getState();

        for(int i=0;i<10;i++){
            if(!thread.getState().equals(stato)){
                stato = thread.getState();
                System.out.println(stato);
            }
            try{
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
   

