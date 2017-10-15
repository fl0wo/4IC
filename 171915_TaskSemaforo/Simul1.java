package task;

/**
 *
 * @author Florian
 */
public class Simul1 {
    
    Simul1(){
        String nomeClasse = this.getClass().getSimpleName();
        System.out.println("Simulazione scelta : "+nomeClasse);
        System.out.println("LA RISORSA é UNA SOLA");
    }
    
    public static void main(String[] meme) {
        new Simul1();
        String contenutoFile = "Contenuto File :D";
        Risorsa[] risorsaMagica = new Risorsa[1];
        for (int i = 0; i < risorsaMagica.length; i++) {
            risorsaMagica[i] = new Risorsa(contenutoFile);
        }
        // 1 perche vogliamo che 1 thread alla volta acceda ad 1 unica risorsa perche il numero di risorse é 1
        Clock contatore = new Clock();
        int nThread = 2;
		int[] posInteressateT1 = {0};
		
		Thread t1 = new Thread(new Scheduler(contatore,risorsaMagica,posInteressateT1));
		t1.start();
    }
}
