
package javathreadsclock;

/**
 *
 * @author giovanni.stevanato
 */
public class JavaThreadsClock {

	public static void main(String[] args) throws InterruptedException {
        Thread[] thread = new Thread[20];
        for (int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(new FileClock(thread, i));
        }
        threadsStart(thread);
    }

    private static void threadsStart(Thread[] thread) {
        for (Thread thread1 : thread) {
            thread1.start();
        }
    }
}
