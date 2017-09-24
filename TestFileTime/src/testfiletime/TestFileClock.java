package testfiletime;

import java.util.concurrent.TimeUnit;

public class TestFileClock {
	public static void main(String[] args) {

        int numeroThread = 5;
		Thread[] thread = new Thread[numeroThread];
                
		for (int numeroDiThread = 0; numeroDiThread < thread.length; numeroDiThread++) {
			thread[numeroDiThread] = new Thread(new FileClock(thread,numeroDiThread));
		}
                
                System.out.println("C'era una volta");
                
		avviaThread(thread);

		try {
			TimeUnit.SECONDS.sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
                
                System.out.println("Fine");
                
	}

	private static void avviaThread(Thread[] thread) {
		for (int i = 0; i < thread.length; i++) {
			thread[i].start();
		}
	}
}
