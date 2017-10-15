package task;


class Nodo<T> {//classe nodo generico della lista

    private T elemento;
    private Nodo prossimo;

    public Nodo(T element) {
        this(element, null);
    }

    public Nodo(T element, Nodo next) {
        this.elemento = element;
        this.prossimo = next;
    }

    public void setNext(Nodo next) {
        this.prossimo = next;
    }

    public T getElement() {
        return elemento;
    }

    public Nodo getNext() {
        return prossimo;
    }
    
    public String toString() {
        return (String) elemento.toString();
    }
}

class Lista<T> {

    private Nodo testa, coda;//nodi sentinella una per la testa e l'altro per la coda
    protected int lunghezza;

    public Lista() {//costruttore
        testa = coda = null;
        lunghezza = 0;
    }

    public void aggiungiAllaCoda(T element) {// aggiungo in coda
        Nodo node = new Nodo(element);
        if (lunghezza==0) {
            testa = coda = node;
        } else {
            coda.setNext(node);
            coda = node;
        }
        lunghezza++;
    }

    public T rimuoviDallAlto() {
        T element = null;
        if (lunghezza == 0) {
            System.out.println("errore coda vuota"); //stampo errore;
        } else {
            element = (T) testa.getElement();
            testa = testa.getNext();
            lunghezza--;
            return element;
        }
        return element;
    }

    public T getPrimo() {
        return (T) this.testa.getElement();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        if (lunghezza != 0) {
            Nodo tmp = testa;
            while (tmp != null) {
                str.append(tmp.toString());
                tmp = tmp.getNext();
            }
        }
        return str.toString();
    }
}

public class Coda<T> {

    private Lista<T> lista;

    public Coda() {
        lista = new Lista<>();
    }

    public int getSize() {
        return lista.lunghezza;
    }

    public void enqueue(T element) {
        lista.aggiungiAllaCoda(element);
    }

    public T dequeue() {
        return lista.rimuoviDallAlto();
    }

    public T front() {
        return lista.getPrimo();
    }
    
    public String toString() {
        return lista.toString();
    }
}
