package verficatpsit;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author florian.sabani
 */
public class CodeThread {
	static Code<Runna> coda;

	public static void main(String[] args) throws InterruptedException {
		coda = new Code<>();
		for (int i = 0; i < 10; i++) {
			coda.enqueue(new Runna(i));
		}
		avviaThread();
	}

	private static void avviaThread() throws InterruptedException {
		for (int i = 0; i < coda.getSize();) {
			Thread t = new Thread(coda.dequeue());
			t.start();
			t.join();
		}
	}

	static class Runna implements Runnable {

		private int posizione;

		private Runna(int i) {
			posizione = i;
		}

		@Override
		public void run() {
			System.out.println(posizione);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ex) {
				Logger.getLogger(CodeThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}
}
