/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfileclock;
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

        System.out.printf("Connessione: %s\n",new Date());

        try {

            TimeUnit.SECONDS.sleep(4);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        System.out.printf("Download finito: %s\n",new Date());

    }

}



class NetworkConnection extends Thread {



    private Thread td; 



    NetworkConnection(Thread td, String name) {

        super(name);

        this.td = td;

    }



    @Override

    public void run() {

        System.out.printf("Network connessa: %s\n", new Date());

        try {

            Thread observer = new Thread(new Observ(Thread.currentThread()));       //create a new thread 
            observer.start();    
            td.join();  

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        System.out.printf("Network non connessa: %s\n",new Date());

    }



    public class Observ implements Runnable {


        private Thread Observable;


        /**
         * 
         * @param currentThread the thread that u want to see the states
         */
        private Observ(Thread currentThread) {
        Observable = currentThread;
        }

        @Override

        public void run() {



            if (Observable.isAlive()) {    
                System.out.println(Observable.getState().name());   //if thread is not killed, it prints the actual state of the thread

            }
            /**
             * untill the thread is running it will do nothing
             * or it will print it a lot of times
             */
            while(Observable.getState()!=Thread.State.BLOCKED){

            }
                //print the state when it change
             System.out.println(Observable.getState().name());

        }



    }

}

