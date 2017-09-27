package testfiletime;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Programmazione concorrente Sabani Florian 4IC Carlo Zuccante
 *
 * @author Florian Sabani
 */
public class FileClock implements Runnable {

    Thread[] threads;
    int posizione;
    Random r = new Random();

    /**
     * Costruttore FileClock.
     *
     * @param threads array di tutti i threads
     * @param posizione posizione del mio thread nell'array "threads".
     */
    public FileClock(Thread[] threads, int posizione) {
        this.threads = threads; // Array con tutti i thread (ci permette di interromperli a vicenda)
        this.posizione = posizione; // La posizione del mio Thread nell array 
    }

    @Override
    public void run() {

        int perQuantiSecondi = 12;  //quanti secondi dovrò stare nel metodo run();
        int dopoQuanto = r.nextInt(perQuantiSecondi);   // dopo quanti secondi dovrò provare ad interrompere qualcuno...

        int aggiorna = 1;   //Ogni aggiorna secondi stampo il mio stato  

        for (int contatoreSecondi = 0; contatoreSecondi < perQuantiSecondi; contatoreSecondi++) {
            // Stampando in quanto sono thread simili si rischia di stampare a schermo in modo disordinato
            //System.out.printf("%s\n", this.posizione);
            System.out.printf("%s\n", new Date());
            try {
                /*
                Sposto il mio Thread nello stato SLEEPING é uno stato molto particolare in quanto di fatto sembrerebbe non faccia nulla
                eh invece é pronto ad essere interrotto , con tanto di clausola catch che in caso di InterruptedException e esegue il codice
                presente sotto...
                 */
                TimeUnit.SECONDS.sleep(aggiorna);   // Dormi , se non vieni interrotto in questo secondo nulla succederà....
            } catch (InterruptedException e) {
                //	System.out.println(Thread.currentThread());
                System.out.println("Infact The FileClock " + this.posizione + " has been interrupted");
                // Dopo l'interuzzione decidio di uscire dal for , in quanto non vorrei mai che il mio Thread venga reinterrotto
                break;  // Uscendo dal for il metodo run non ha altro da fare e il mio Thread muore :(
                //A meno che non evochi un altro Thread prima di morire, (si parlerebbe di stato Zombie o Thread Zombie) <3 <3 <3 <3
                //Zombie perchè anche dopo la morte continua a vivere , come gli Zombie ;-)
            }
            // Se é arrivato il momento di interrompere qualcuno lo facciamo
            if (contatoreSecondi >= dopoQuanto) {
                this.interrompiUnThread();
                contatoreSecondi = contatoreSecondi + r.nextInt(perQuantiSecondi - contatoreSecondi);
            }
            if (ultimoThreadRimasto()) {
                System.out.println("The " + this.posizione + "th Thread is the last alive ");
                System.out.println("So it suicides");
                // Mi interrompo passandogli come parametri la mia posizione e il mio thread 
                // Thread.currentThread() e come dire this.threads[this.posizione];
                //this.interrompiThread(this.posizione, Thread.currentThread());
                return;
            }
        }
    }

    /**
     * Interrompe un thread preso con criteri casuali.
     */
    private void interrompiUnThread() {
        // Se non sono lultimo thread rimasto prendo un thread da interrompere
        int numeroPos = prendiUnThread();
        Thread c = this.threads[numeroPos];
        // E lo interrompo
        this.interrompiThread(numeroPos, c);

    }

    /**
     * Indica se nell'array threads non sono rimasti altri thread oltre che al
     * mio.
     *
     * @return se nell'array threads non sono rimasti altri thread oltre che al
     * mio.
     */
    private boolean ultimoThreadRimasto() {
        // Scrollo nell'array di Thread tutti i thread
        for (int i = 0; i < this.threads.length; i++) {
            // E mi chiedo se c'é né uno che é sia in vita(quindi non interrotto) 
            // e allo stesso momento non devo essere io in quanto é ovvio che io sono in vita
            // voglio sapere se oltre a me ci sono altri Thread....
            if (this.threads[i].isAlive() && this.threads[i] != Thread.currentThread()) {
                // Se ce un Thread vivo e non lo sono io vuol dire che ci sono altri Thread oltre a me 
                return false;   // Quindi false
            }
        }
        return true;    // Se ho fatto tutto il for senza returnare returno true;
    }

    /**
     * Interrompe un Thread dato.
     *
     * @param pos la posizione nell'array del Thread da interrompere.
     * @param daInterrompere il Thread da interrompere.
     */
    private void interrompiThread(int pos, Thread daInterrompere) {
        //Prima di interrompere il Thread , mi chiedo se non e gia interrotto,
        //in quanto la doppia interruzione porterebbe alla ripresa...
        if (!daInterrompere.isInterrupted()) {
            // Se la posizione del thread che ha interrotto é diversa da quella 
            // del thread che é stato interrotto 
            if (pos != this.posizione) {
                System.out.println("\n Thread " + this.posizione + " interrumpted the Thread " + pos);
            } else {
                //Altrimenti si tratta di self interruption 
                // Ossia il Thread sta tentando di interrompersi da solo
                System.out.println("The Thread " + this.posizione + " as last alive , decided to commit suicide :D");
            }

            // In ogni caso il Thread lo interrompo...
            daInterrompere.interrupt();
        }
    }

    /**
     * Restituisce un Thread assicurandosi che non sia il mio stesso Thread.
     *
     * @return un Thread escrapolandolo in modo casuale dall'array.
     */
    private int prendiUnThread() {
        // Genero (tramite la classe Random) un numero casuale da 0 ad un max 
        //che é la lunghezza dell'array di Thread
        int posizioneCasuale = r.nextInt(this.threads.length);

        // Fino a quando la classe Random che é fatta bene genera sempre il mio stesso numero 
        while (posizioneCasuale == this.posizione) {
            // Rigenero un altro numero sperano che la classe Random faccia il suo lavoro 
            posizioneCasuale = r.nextInt(this.threads.length);
            // In quanto voglio interrompere me stesso SOLO se sono lultimo thread in vita
        }
        return posizioneCasuale;
    }
}
