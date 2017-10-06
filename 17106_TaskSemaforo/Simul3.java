package task;

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

    public static void main(String[] meme) {

        new Simul3();

        String contenutoFile = "Contenuto File3 :D";

        Risorsa[] risorsaMagica1 = new Risorsa[2];
        for (int i = 0; i < risorsaMagica1.length; i++) {
            risorsaMagica1[i] = new Risorsa(contenutoFile);
        }

        // 1 perche vogliamo che 1 thread alla volta acceda ad 1 unica risorsa perche il numero di risorse é 1
        CodaTask coda = new CodaTask(risorsaMagica1.length);
        int nThread = 3;
        Thread[] tantiTask = new Thread[nThread];

        for (int i = 0; i < nThread; i++) {
            for (int j = 0; j < risorsaMagica1.length; j++) {
                // Gli affido entrambe le risorse mamma mia che pro che sono.
                tantiTask[i] = new Thread(new Task(coda, risorsaMagica1, j));// i in quanto parliamo del secondo esempio   
            }
            tantiTask[i].start();
        }
    }
}
