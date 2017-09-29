package testdatadownload;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestDataDownload {
	public static void main(String[] args) {
		DataDownloader ddl = new DataDownloader();
		Thread td = new Thread(ddl, "DataDownloaderThread"); //NEW
		Thread tn = new NetworkConnection(td, "NetworkConnectionThread"); //NEW
		tn.start();//tn e td diventano ready
		td.start();//da NEW
	}
}		/*nel programma c'e solo  il join e non il fork, 
		che sarebbe pero' il main, dal quale partono gli altri thread 
		e quindi si crea il concetto di fork, quindi si puo dire che
		"fork" è sottointeso in java*/

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

class NetworkConnection extends Thread implements Osservabile {//implementazione di Osservable perchè questa e la classe da osservare

	private Thread td; // the downloadr
	private Osserva o; //creazione di un osservatore

	NetworkConnection(Thread td, String name) {
		super(name);
		this.td = td;
	}

	@Override
	public void run() {
		System.out.printf("Network connected: %s\n",
				new Date());
		try {
			Thread osservatore = new Thread(new Osserva(Thread.currentThread( ))); //Creo un nuovo thread che osserva
			osservatore.start(); //il thread osservatore va in stato di ready
                        Thread.sleep(10);   // per provare anche il TIMED_WAITING
			td.join(); //aspetta che tu finisca(e dopo parto io)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Network not connected: %s\n",
				new Date());
	}

	@Override
	public void registraOsservatore(Osservatore osservatore) {
		this.o = (Osserva) osservatore;
	}

	@Override
	public void notificaOsservatore() {
		this.o.update();
	}
	
	public class Osserva implements Runnable, Osservatore{
		private Thread thread;
		@Override
		public void run() {
                         System.out.println("Passato a :" +this.thread.getState());    // runnable
                    
                        while(!this.thread.getState().name().equals("TERMINATED")){
                            String statoPrecedente = this.thread.getState().name();
                            while(statoPrecedente.equals(this.thread.getState().name()));   // cambiamento stato
                            
                            System.out.println("Passato da:  "+statoPrecedente+ " A :   "+this.thread.getState());
                        }
                        
                        System.out.println("Passato a :" +this.thread.getState()); // terminato
		}

		public Osserva(Thread thread) {
			this.thread = thread;
		}

		@Override
		public void update() {
			System.out.println(Thread.currentThread().getState());
		}

		@Override
		public void setOsservabile(Osservabile osservabile) {
			this.thread= (Thread) osservabile;
		}
		
	}
}
