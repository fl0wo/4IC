package threadsort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author Florian
 */
public class OrdinamentoParallelo {

    boolean finito = false;

    String CRITERIO_ORDINAMENTO = "Bubble Sort";

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
            int mezzo = separa(array, inizio, fine);
            /// Ho usato lambda <3
            Thread sinistra = new Thread(() -> {
                ordina(nThreads - 1, array, inizio, mezzo);
            });
            sinistra.start(); // Ohhh Somma Scheduler prendilo!
            Thread destra = new Thread(() -> {
                ordina(nThreads - 1, array, mezzo, fine);
            });
            destra.start(); // Somma Scheduler prendilo!

            try {
                destra.join();
                sinistra.join();
            } catch (InterruptedException ex) {
            }

        } else {
            criterioOrdinamento(array,inizio, fine);   // Non uso il semaforo perche ogni thread si occupa di un pezzo diverso <3 
            //(quindi accedo alla stessa risorsa si , ma a pezzi diversi)
        }
    }

    public void ordinaParallelo(int[] array, int nThreads) throws InterruptedException {
        Thread td = new Thread(() -> {
            ordina(nThreads, array, 0, array.length);
        });
        td.start();
        td.join();
        System.out.println(Arrays.toString(array));
    }

    public void criterioOrdinamento(int[] array, int da, int a) {
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

}
