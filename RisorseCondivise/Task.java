package tasksemaforo;

import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class Task {

    private Random random;
    private Risorsa[] risorse;
    private String nome;

    public Task(Risorsa[] arrayRisorse, String nome) {
        this.nome = nome;
        this.risorse = arrayRisorse;
        random = new Random((new Date()).getTime());
    }

    public Task(Risorsa singola, String nome) {
        this.nome = nome;
        risorse = new Risorsa[1];
        risorse[0] = singola;
    }

    public void job() {
        for (int pos = 0; pos < risorse.length; pos++) {
            try {
                risorse[pos].semaphore.acquire(); //accedo alla risorsa in pos pos
                notificaAquisizione(risorse[pos]);
            } catch (InterruptedException ex) {
                break;
            } finally {// Questo finnally serve che anche se venissi interrotto rilascio la risorsa prima di decedere
                synchronized(risorse[pos].semaphore){
                    notificaRilascio(risorse[pos]);
                    risorse[pos].semaphore.release();
                }
            }
        }
    }

    private void notificaAquisizione(Risorsa risorsa) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(this.nome+" ha in mano il : "+risorsa.value);
    }

    private void notificaRilascio(Risorsa risorsa) {
        System.out.println(this.nome+" ha lasciato il : "+risorsa.value);
    }
}
