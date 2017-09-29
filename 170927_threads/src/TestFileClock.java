import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TestFileClock {
    private static final int NUMERO_THREAD = 10;

    public static ArrayList<FileClock> threads;
    public static void main(String[] args) {
        /*FileClock clock = new FileClock();
        Thread thread = new Thread(clock);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish");*/
        //Thread[] thread = new Thread[NUMERO_THREAD];
        threads =  new ArrayList<>();

        for(int i=0; i<NUMERO_THREAD; i++){
            threads.add(new FileClock(i));
        }

        for(int i=0; i<threads.size(); i++){
            threads.get(i).start();
        }

        while(threads.size() > 1){
            for(int i=0; i<threads.size(); i++){
                if(!threads.get(i).isAlive()){
                    System.out.println("[ARENA] Il thread(" + threads.get(i).getClockId() + ") è morto");
                    threads.remove(i);
                }
            }
        }
        System.out.println("[ARENA] Il Thread(" + threads.get(0).getClockId() + ") ha vinto");
        try {
            sleep(500);
        }
        catch (InterruptedException e) {
            //e.printStackTrace();
        }
        return;
    }
}
