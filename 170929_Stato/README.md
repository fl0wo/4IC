# TEST STATI THREAD
In questo programma viene eseguito un osservatore che controlla lo stato di un thread in esecuzione.
Questo osservatore consiste in un oggetto Runnable (che quindi viene eseguito in un Thread differente) che ha come campo il Thread da osservare, e dentro un while controlla se esso cambia stato finchè il suo stato non muta in "TERMINATED".