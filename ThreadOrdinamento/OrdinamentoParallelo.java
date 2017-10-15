package threadsort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class OrdinamentoParallelo {

    boolean finito = false;
    public final String ROSSO = "\u001B[31m";
    public final String VERDE = "\u001B[32m";
    public final String RESET = "\u001B[0m";
    public final String BLU = "\u001B[34m";

    private int haFinito = 0,nThreadRichiesti;
    private Semaphore s = new Semaphore(1);

    int separa(int array[], int inzio, int fine) {
        int i = inzio, j = fine - 1;
        int temporaneo;
        int valoreMezzo = array[(inzio + fine - 1) / 2];

        while (i <= j) {
            /*
             * Portiamo i e j ai lati del centro
             */
            while (array[i] < valoreMezzo) {
                i++;    // mi avvicino al mexxo
            }
            while (array[j] > valoreMezzo) {
                j--;    // mi avvicino al mezzo
            }

            if (i <= j) {
                //swappo 
                temporaneo = array[i];
                array[i] = array[j];
                array[j] = temporaneo;
                i++;
                j--;
            }
        };

        return i;
    }

    private void ordina(int nThreads, int[] array, int inizio, int fine) {
        
        if (nThreads > 1) {
            
            try {
                s.acquire();
                stampa(array, inizio, fine);
            } catch (InterruptedException ex) {
            } finally {
                s.release();
            }

            int mezzo = separa(array, inizio, fine);

/// Ho usato lambda <3
            Thread parteSinistra = new Thread(() -> {
                if (!isOrdinato(array, inizio, mezzo)) {
                    // Non ordino qualcosa di gia ordinato..
                    ordina(nThreads - 1, array, inizio, mezzo);
                } else if (nThreads == 2) {
                    //chiediSemaforo(Thread.currentThread().getName(), "Ho ordinato il mio pezzettino.");
                }
            });parteSinistra.setName("ThreadSinistra "+nThreads);parteSinistra.start(); // Ohhh Somma Scheduler prendilo!
            

            Thread parteDestra= new Thread(() -> {
                if (!isOrdinato(array, mezzo, fine)) {
                    // Non lo ordino se lo é gia...
                    ordina(nThreads - 1, array, mezzo, fine);
                } else if (nThreads == 2) {
                    //chiediSemaforo(Thread.currentThread().getName(), "Ho ordinato il mio pezzettino.");
                }
            });parteDestra.setName("ThreadDestra "+nThreads);parteDestra.start(); // Somma Scheduler prendilo!
        } else {
            haFinito++;
            if (!isOrdinato(array, inizio, fine)) {
                try {
                    s.acquire();
                    //stampa(array, inizio, fine);
                    ordinamento(array, inizio, fine);
                    stampa(array, inizio, fine);
                    //stampa(array, inizio, fine);
                } catch (InterruptedException ex) {
                    // Se venissi interrotto qui almeno nonostante io sia stato interrotto
                } finally {
                    s.release();    // rilascio il permesso
                    //Uno dei Thread ha finito.
                }
            }
          
        }
    }

    void chiediSemaforo(String chiVuoleStampare, String daStampare) {
        try {
            s.acquire();
            System.out.println("Il Sotto" + chiVuoleStampare + " dice : " + daStampare);
            //stampa(array, inizio, fine);
        } catch (InterruptedException ex) {
        } finally {
            s.release();
        }
    }

    void ordinaParallelo(int[] array, int nThreads) {
        nThreads = array.length / nThreads >= 10 ? nThreads : array.length / 10;
        this.nThreadRichiesti = nThreads;
        stampa(array, 0, array.length);
        ordina(nThreads, array, 0, array.length);
    }

    private void ordinamento(int[] array, int da, int a) {
        // Ho usato il bubble in quanto il piu comodo da scrivere
        for (int i = da; i < a; i++) {
            boolean trovatoErrore = false;
            for (int j = 0; j < a - 1; j++) {
                if (array[j] > array[j + 1]) {
                    //Swap
                    int k = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = k;
                    trovatoErrore = true;
                }
            }
            if (!trovatoErrore) {
                break;
            }
        }
    }

    public int[] randomArray(int n, int min, int max) {
        int[] arrayCasuale = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            arrayCasuale[i] = min + r.nextInt(max - min);
        }
        return arrayCasuale;
    }

    private boolean isOrdinato(int[] array, int inizio, int fine) {
        for (int i = inizio; i < fine - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private void stampa(int[] array, int inizio, int fine) {
        fine = fine > array.length ? array.length : fine;
        System.out.println("");
        for (int i = 1; i < array.length; i++) {
            if (i >= inizio && i < fine - 1) {
                if (array[i] > array[i + 1] || array[i] < array[i - 1]) {
                    System.out.print(ROSSO + " " + array[i] + "" + RESET);
                } else {
                    System.out.print(VERDE + " " + array[i] + "" + RESET);
                }
            } else {
                System.out.print(BLU + " " + array[i] + "" + RESET);
            }
        }
    }

}
