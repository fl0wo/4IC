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
        for (int i = 0; i < risorsaMagica1.length; i++) {
            risorsaMagica1[i] = new Risorsa(contenutoFile[i]);
        }
        // 1 perche vogliamo che 1 thread alla volta acceda ad 1 unica risorsa perche il numero di risorse é 1
        Clock contatore = new Clock();
        int nThread = 2;
		int[] posInteressateT1 = {0,1};
		int[] posInteressateT2 = {0,1};
		Thread t1 = new Thread(new Scheduler(contatore,risorsaMagica1,posInteressateT1));
		Thread t2 = new Thread(new Scheduler(contatore,risorsaMagica1,posInteressateT2));
		t1.start();t2.start();
	}
}
