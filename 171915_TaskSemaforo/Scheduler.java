package task;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class Scheduler implements Runnable {

    public static final String RESET = "\u001B[0m";
    public static final String ROSSO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";

    Clock cronometro;
    Risorsa[] risorse;
    private int[] risorsaCheMiInteressa;

    public Scheduler(Clock cronometro, Risorsa[] risorsa, int[] posRisorsa) {
        this.risorse = risorsa;
        this.cronometro = cronometro;
        cronometro.addThread(Thread.currentThread());
        this.risorsaCheMiInteressa = posRisorsa;
    }

    @Override
    public void run() {
        System.out.printf(VERDE + " " + Thread.currentThread().getName() + " Pronto ad usare la risorsa :D\n" + RESET);
		// Richiedo alla coda se posso accedere alla risorsa, se la risorsa é gia occupata da qualcunaltro(thread)
        //allora la coda mi mettera in coda e appena sarà libero di nuovo potrò accedere alla risorsa.+
        Coda<Thread> codaScheduler = new Coda();
        for (int i = 0; i < risorsaCheMiInteressa.length; i++) {

            Thread sk1 = new Thread(new Task(getRisorsa(risorsaCheMiInteressa[i]), Thread.currentThread().getName()));
            codaScheduler.enqueue(sk1);
            sk1.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (int i = 0; i < codaScheduler.getSize();) {
            try {
                codaScheduler.dequeue().join();
            } catch (InterruptedException ex) {
                System.out.println("Interrotto durante lattesa delle operazioni con le risorse");
            }
        }

        System.out.printf(Thread.currentThread().getName() + ": Ho finito di usare la risorsa :D\n");
    }

    private Risorsa getRisorsa(int i) {
        try {
            risorse[i].s.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return this.risorse[i]/*.getRisorsaPreziosa()*/;
        }
    }

    class Task extends Thread {

        Risorsa risorsa;
        Random r = new Random();
        String dachi;

        public Task(Risorsa r, String daChi) {
            this.risorsa = r;
            this.dachi = daChi;
        }

        @Override
        public void run() {
            try {
                synchronized (risorsa) {
                    this.utilizzaRisorsa(risorsa);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                risorsa.s.release();
                System.out.println("Finito di usare la risorsa " + risorsa.getRisorsaPreziosa());
            }
        }

        public void utilizzaRisorsa(Risorsa risorsa) throws InterruptedException {
            int massimo = 4, minimo = 1;
            double durataUtilizzo = tempoCasuale(minimo, massimo);// da 1 a 4 secondi
            long durataNanoSecondi = (long) (durataUtilizzo * 1000000.0);// convertire da Secondi (con la virgola) a nanosecondi
            int nCicli = 3 + r.nextInt(3);
            System.out.println(dachi + " sta usato la risorsa : " + Scheduler.ROSSO + risorsa.getRisorsaPreziosa() + Scheduler.RESET + " ...");
            int percentualeAttesa = 0;
            String ben = durataUtilizzo < (massimo + minimo) / 2 ? "solo" : "ben";
            String coloreScritta = ben.equals("ben") ? Scheduler.ROSSO : Scheduler.VERDE; // Se ha impiegato molto tempo il task lo scrivo in rosso altrimenti in verde
            Thread.sleep(20);//Un po di distacco dal eventuale messaggio precedente
            // Il metodo utilizza oltre a fare tutta questa(sabaniflorian) scena modifica anche la risorsa:
            String alfabeto = "abcdefghflorianilmnopqrsabanituvzABCDEFGHILMNOPQRSTUVZ";
            for (int i = 0; i < nCicli; i++) {
                System.out.println("... " + percentualeAttesa + "%" + "\t\t\t Risorsa : [" + "" + risorsa + "" + Scheduler.RESET + "];" + "\t valore : " + risorsa.getRisorsaPreziosa());  // prettamente figo.
                TimeUnit.MICROSECONDS.sleep(durataNanoSecondi / nCicli);
                percentualeAttesa += 100 / nCicli;
                risorsa.setValoreRisorsaPreziosissima(risorsa.getRisorsaPreziosa() + alfabeto.charAt(r.nextInt(alfabeto.length())));  // modifico la risorsa :D
            }
            System.out.println(dachi + " ha finito ... " + 100 + "%");  // scrivo 100 per evitare il problema del 99%.

            System.out.println(this.dachi + " ha usanto la risorsa : " + risorsa.getRisorsaPreziosa() + " per " + coloreScritta + ben + " " + durataUtilizzo + Scheduler.RESET + " secondi");
        }

        private double tempoCasuale(int minimo, int massimo) {
            // nextDouble restituisce un double casuale da 0 a 1
            return minimo + r.nextDouble() * (minimo + massimo);
        }
    }
}
