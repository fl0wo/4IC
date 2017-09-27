package testfiletime;

import java.util.concurrent.TimeUnit;

public class TestFileClock {

    public static void main(String[] args) {

        int numeroThread = 10;
        Thread[] thread = new Thread[numeroThread];

        for (int numeroDiThread = 0; numeroDiThread < thread.length; numeroDiThread++) {
            thread[numeroDiThread] = new Thread(new FileClock(thread, numeroDiThread));
        }

        System.out.println("C'era una volta");

        avviaThread(thread);

        boolean ancoraVivi = true;
        
        while (ancoraVivi(thread)) {
        }

        System.out.println("Morti tutti :C");

        try {
            TimeUnit.SECONDS.sleep(1);
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

    private static boolean ancoraVivi(Thread[] threads) {
        // Scrollo nell'array di Thread tutti i thread
        for (int i = 0; i < threads.length; i++) {
            // E mi chiedo se c'é né uno che é sia in vita(quindi non interrotto) 
            // e allo stesso momento non devo essere io in quanto é ovvio che io sono in vita
            // voglio sapere se oltre a me ci sono altri Thread....
            if (threads[i].isAlive()) {
                // Se ce un Thread vivo e non lo sono io vuol dire che ci sono altri Thread oltre a me 
                return true;   // Quindi true
            }
        }
        return false;    // Se ho fatto tutto il for senza returnare returno flase;
    }
}
