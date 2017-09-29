package testfileclock;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author giacomo.ravagnan
 */
public class TestFileClock extends Thread{
public static void main(String[] args) {
    
        int numThread = 15;
		Thread[] thread = new Thread[numThread];

		for (int contThread = 0; contThread < thread.length; contThread++) {
			thread[contThread] = new Thread(new FileClock(thread,contThread));
		}
		StartThread(thread);
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
                System.out.println("stop");
	}
	private static void StartThread(Thread[] thread) {

		for (int i = 0; i < thread.length; i++) {

			thread[i].start();

		}

	}

}
    

