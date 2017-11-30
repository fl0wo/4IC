import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author florian.sabani
 */
public class TrovaMax {
	
	static CountDownLatch finite;
	
	public static void main(String[] args) throws InterruptedException {
		int[] a = arrayRandom(20, 20, 120);
		System.out.println(Arrays.toString(a));
		int max = trovaMax(a, 9); // 9 sono i numero di Thread
		System.out.println(max);

	}

	private static int trovaMax(int[] a, int nThread) throws InterruptedException {
		int lunghezzaPezzo = a.length / nThread;
		Integer[] max = new Integer[nThread];
		for (int i = 0; i < nThread; i++) {
			max[i] = new Integer(a[0]);
		}
		
		finite = new CountDownLatch(nThread-1);

		for (int i = 0, pos = 0; i < nThread; i++, pos += lunghezzaPezzo) {
			Thread t = new Thread(new Max(a, pos, pos + lunghezzaPezzo, max, i));
			t.start();
		}

		try {
			finite.await();
		} catch (InterruptedException E) {
		}finally{

		}

		int m = max[0];
		for (int i = 0; i < max.length; i++) {
			if (max[i] > m) {
				m = max[i];
			}
		}
		return m;
	}

	private static int[] arrayRandom(int nElementi, int min, int max) {
		Random r = new Random();
		int[] a = new int[nElementi];
		for (int i = 0; i < nElementi; i++) {
			a[i] = min + r.nextInt(max - min);
		}
		return a;
	}
}
