*Descrizione Veloce.*

# Simulazioni Risorsa Condivisa

*Più Thread vogliono accedere alla stessa risorsa*



## 1. Come impedirlo

*Con la classe Semaforo.*

### 1.1 Perchè impedirlo

*Lo impediamo in quanto si potrebbero verificare modifiche non volute.*



## 2. Classe Semaforo

*E' un una classe che può avere n permessi dove si può accedere ai suoi permessi , usarli , per poi rilasciarli.*

## 3. Come funziona

*Nel mentre che io accedo ad una risorsa , il permesso del semaforo presente nella risorsa da 1 passa a 0
 Se qualche altro Thread volesse accedere a quella risorsa, viene messo in coda (fino a quando non si libera)
 in quanto la risorsa non é maggiore di 0.*

### 3.1 Task #1

*Ha la funzione di usare la risorsa in un modo certo.*

Metodi principali:

| Metodo   | Descrizione                                          |
| -------- | ---------------------------------------------------- |
| JOB      | + void : come verrà usata la risorsa                 |
| NOTIFICAA| + void : notifica tutti che ha la risorsa            |
| NOTIFICAR| + void : notifica tutti che non ha piu la risorsa   |


## Classe Schedular

*Dati i thread li gestisce.*

Nel gestirli fa si che ogni thread possa usare anche nello stesso istante più risorse.

    public Scheduler(Task[] arrayTask) {
        this.arrayTask = arrayTask;
    }

## Classe Risorsa

*E una classe che tra i campi ha la risorsa che verrà richiesta.*

Ha una classe Semaphore come campo che ha come n permessi settato ad 1.
Il valore di risorsa é un tipo Object per convenzione.

    public Risorsa(int numero) {
        semaphore = new Semaphore(1, true);
    }

