# Task Semaforo

Un codice che prova a gestire i thread, con l'ausilio della classe Semaphore(Semaforo), impedendogli di accedere alla stessa Risorsa.

## Risorsa

Risorsa é la classa alla quale i thread vorrebbero accedere per usarla.

## CodaTask 

Controlla che i task accedano a risorse diverse uno alla volta , mettendo in coda quelli che vorrebberro accederci mentre la risorsa 
risulta occupata.

## Task

il Runnable (il nostro "Thread") che prova ad accedere alla risorsa per stamparne le informazioni.

## Osserva

Thread osservatore che ci dice in che stato si trova il thread "Task" mentre aspetta in CodaTask che il "Semaforo" torni verde per lui
e finalmente possa accedere alla risorsa.

## Costruttore Task

    CodaTask coda;
    Risorsa[] risorse;
    private int risorsaCheMiInteressa;
    
    public Task(CodaTask coda, Risorsa[] risorsa, int posRisorsa) {
        this.coda = coda; 
        this.risorse = risorsa;
        this.risorsaCheMiInteressa = posRisorsa;
    }
    
    Impostati i campi il nostro Thread Task può provare ad accedere alla risorsa :
    ------------------------------------------------------------------------------
    coda.accediRisorsa(getRisorsa(risorsaCheMiInteressa));
    ------------------------------------------------------------------------------
    private Object getRisorsa(int i) {
        return this.risorse[i].getRisorsaPreziosa();
    }

## Costruttore CodaTask
  
    private final Semaphore semaforo;  // il nostro semaforo
    private final int MASSIMO_NUMERI_DI_RISORSE; // il numero di risorse :D
    private final Random r;

    public CodaTask(final int nPermessi) {
        r = new Random();
        this.MASSIMO_NUMERI_DI_RISORSE = nPermessi;
        this.semaforo = new Semaphore(MASSIMO_NUMERI_DI_RISORSE);
    }
    
    /////////////////////////////////////////////////// ACCEDI ALLA RISORSA ////////////////////////////////////////////////////////////
    
    public void accediRisorsa(Object risorsa) throws InterruptedException {
        //Spiegato nel file "commento.dm" la differenza tra drainPermits e availablePermits...secondo me :D
        if (/*semaforo.drainPermits() < MASSIMO_NUMERI_DI_RISORSE - 1*/semaforo.availablePermits() < 1) {  // Non ce nemmeno 1 permesso disponibile
            // Sono curioso di sapere che stato ha il thread se invece le/a risorse/a sono occupate :D
            Thread osserva = new Thread(new Osserva(Thread.currentThread()));// Osservo come si comporterà il mio thread
            osserva.start(); // Osservo...
        }
        // mi prendo la risorsa , se occupata il semaforo mi mette in coda d'attesa:
        semaforo.acquire(1);    // in realta scrivere acquire() é come scrivere acquire(1)
        //Una volta acquisita la risorsa la maneggio per random secondi :
        utilizzaRisorsa(risorsa);
        // ora la risorsa tornerà ad essere libera quindi riutilizzabile
        semaforo.release(1); // in realta scrivere release() é come scrivere release(1)
    }

  /////////////////////////////////////////////////// UTILIZZO LA RISORSA ////////////////////////////////////////////////////////////

    private void utilizzaRisorsa(Object risorsa) throws InterruptedException {
        int massimo = 4,minimo =1;
        double durataUtilizzo = tempoCasuale(minimo, massimo);// da 1 a 4 secondi
        long durataNanoSecondi = (long) (durataUtilizzo * 1000000.0);// convertire da Secondi (con la virgola) a nanosecondi
        int nCicli = 3 + r.nextInt(3);
        System.out.println(Thread.currentThread().getName() + " sta usato la risorsa : " + Task.ROSSO + risorsa + Task.RESET + " ...");
        int percentualeAttesa = 0;
        String ben = durataUtilizzo < (massimo+minimo)/2 ? "solo" : "ben";
        String coloreScritta = ben.equals("ben") ? Task.ROSSO : Task.VERDE; // Se ha impiegato molto tempo il task lo scrivo in rosso altrimenti in verde
        Thread.sleep(20);//Un po di distacco dal eventuale messaggio precedente
        for (int i = 0; i < nCicli; i++) {
            System.out.println("... " + percentualeAttesa + "%");  // prettamente figo.
            TimeUnit.MICROSECONDS.sleep(durataNanoSecondi / nCicli);
            percentualeAttesa += 100 / nCicli;
        }
        System.out.println("Finito ... " + 100 + "%");  // scrivo 100 per evitare il problema del 99%.
        System.out.println(Thread.currentThread().getName() + " ha usanto la risorsa : " + risorsa + " per "+coloreScritta+ ben +" "+ durataUtilizzo + Task.RESET + " secondi");
    }
    
    /////////////////////////////////////////////////// TEMPO CASUALE //////////////////////////////////////////////////////////////////
    
    private double tempoCasuale(int minimo, int massimo) {
        // nextDouble restituisce un double casuale da 0 a 1
        return minimo + r.nextDouble() * (minimo + massimo);
    }
    
    ## Osserva Costruttore
    
    public Osserva(Thread daOsservare) {
        this.daOsservare = daOsservare;
    }
    
    ///////////////////////////////////////////// STAMPO LE INFORMAZIONI DELLA RISORSA //////////////////////////////////////////////////
    
    private String aggiorna() {
        return "Il Thread : " + daOsservare.getName() + "  IN CODA CON LO STATO : " + daOsservare.getState().name();
    }
    
    
    Ci sto ancora lavorando ......
    
    ## Autori

* **Sabani Florian** - *Unico collaboratore* - [fl0wo](https://github.com/fl0wo)
    
