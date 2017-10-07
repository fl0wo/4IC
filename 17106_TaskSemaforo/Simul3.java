package task;

import java.util.Random;

/**
 *
 * @author Florian
 */
public class Simul3 {

    Simul3() {
        String nomeClasse = this.getClass().getSimpleName();
        System.out.println("Simulazione scelta : " + nomeClasse);
        System.out.println("LE RISORSE SONO DUE E I THREAD PROVANO AD ACCERCI IN MODO ANALOGO \n(PRIMA LA PRIMA POI LA SECONDA) ");
    }

    public static void main(String[] meme) throws InterruptedException {

        new Simul3();

        Random r = new Random();
        String[] contenutoFile = new String[r.nextInt(20)];
        for (int i = 0; i < contenutoFile.length; i++) {
            contenutoFile[i] = "Contenuto^ " + i + " :D";
        }

        Risorsa[] risorseMagiche = new Risorsa[2];
        String casuale = contenutoFile[r.nextInt(contenutoFile.length)];
        for (int i = 0; i < risorseMagiche.length; i++) {
            System.out.println(casuale);
            risorseMagiche[i] = new Risorsa(casuale);
            casuale = contenutoFile[r.nextInt(contenutoFile.length)];
        }

        // 1 perche vogliamo che 1 thread alla volta acceda ad 1 unica risorsa perche il numero di risorse é 1
        CodaTask coda = new CodaTask(risorseMagiche.length);
        int nThread = 2;
        Thread[] tantiTask = new Thread[nThread];
        //Per sovrapporre le due risorse quindi usare entrambe le risorse nello stesso istante( due risorse diverse) dallo stesso threa
        // bisogna passargli un array di risorseCheMiInteressano (int)
        int[] risorseCheVuoleThread1 = {0, 1};
        int[] risorseCheVuoleThread2 = {1, 0};
        // QUESTO E UN CASO PARTICOLARISSIMO NELLA QUALE 2 THREAD POTREBBERO FINIRE IN DEAD LOCK
        // E IL NOSTRO COMPITO E QUELLO DI IMPEDIRGLIELO!

        tantiTask[0] = new Thread(new Task(coda, risorseMagiche, risorseCheVuoleThread1));// j in quanto parliamo del secondo esempio   
        tantiTask[0].start();
        Thread.sleep(100);  // USIAMO LA RISORSA 1 MA DOPO UN PO INIZIAMO AD USARE ANCHE LA RISORSA 
        tantiTask[1] = new Thread(new Task(coda, risorseMagiche, risorseCheVuoleThread2));// j in quanto parliamo del secondo esempio   
        tantiTask[1].start();
    }
}
