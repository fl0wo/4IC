package task;

import java.util.Random;

/**
 *
 * @author Florian
 */
public class Simul2 {

    Simul2() {
        String nomeClasse = this.getClass().getSimpleName();
        System.out.println("Simulazione scelta : " + nomeClasse);
        System.out.println("LE RISORSE SONO DUE E I THREAD PROVANO AD ACCERCI IN MODO ANALOGO \n(PRIMA LA PRIMA POI LA SECONDA) ");
    }

    public static void main(String[] meme) throws InterruptedException {

        new Simul2();
        Random r = new Random();
        String[] contenutoFile = new String[r.nextInt(20)];
        for (int i = 0; i < contenutoFile.length; i++) {
            contenutoFile[i] = "Contenuto^ " + i + " :D";
        }

        Risorsa[] risorsaMagica1 = new Risorsa[2];
        int nCaso = r.nextInt(contenutoFile.length);
        String casuale = contenutoFile[nCaso];
        for (int i = 0; i < risorsaMagica1.length; i++) {
            System.out.println(casuale);
            risorsaMagica1[i] = new Risorsa(casuale);
            int nCaso1 = r.nextInt(contenutoFile.length);
            while(nCaso1==nCaso){
                nCaso1 = r.nextInt(contenutoFile.length);
            }
            nCaso = nCaso1;
            casuale = contenutoFile[nCaso1];
        }

        // 1 perche vogliamo che 1 thread alla volta acceda ad 1 unica risorsa perche il numero di risorse é 1
        CodaTask coda = new CodaTask(risorsaMagica1.length);
        int nThread = 2;
        Thread[] tantiTask = new Thread[nThread];
        //Per sovrapporre le due risorse quindi usare entrambe le risorse nello stesso istante( due risorse diverse) dallo stesso threa
        // bisogna passargli un array di risorseCheMiInteressano (int)
        int[] risorseInteressateContemporaneamente = new int[risorsaMagica1.length];
        
        for (int j = 0; j < risorsaMagica1.length; j++) {
            risorseInteressateContemporaneamente[j] = j;
        }
        
        for (int i = 0; i < nThread; i++) {
            tantiTask[i] = new Thread(new Task(coda, risorsaMagica1, risorseInteressateContemporaneamente));// j in quanto parliamo del secondo esempio   
            tantiTask[i].start();
            Thread.sleep(100);  // USIAMO LA RISORSA 1 MA DOPO UN PO INIZIAMO AD USARE ANCHE LA RISORSA 
        }
    }
}
