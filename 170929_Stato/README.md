# Ragazzo Matteo
## Stati dei thread
Ho creato un nuovo thread chiamato StateWatch il cui compito è quello di stampare lo stato degli altri thread. La sua funzione stampa un nuovo messaggio ogni volta che il thread passa ad un stato diverso da quello precedente.
Appena viene creato il thread il suo stato è "NEW", dopo la funzione .start() ,ossia quella che esegue il thread, lo stato del thread passa da "NEW" a "RUNNABLE". I thread passano poi allo stato "WAITING" e "TIMED_WAITING" per, infine, passare allo stato "TERMINATED" ossia quando non sono più in esecuzione.

