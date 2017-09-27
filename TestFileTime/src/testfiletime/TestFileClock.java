package testfiletime;

import java.util.concurrent.TimeUnit;

public class TestFileClock {

    public static void main(String[] args) {
        Thread[] thread = new Thread[10];
        for (int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(new FileClock(thread,i));    // nonostante thread non sia ancora full gli passo il reference
        }
        avviaThread(thread);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish");
    }

    public static void avviaThread(Thread[] t) {
        for (int i = 0; i < t.length; i++) {
            t[i].start();
        }
    }
}
