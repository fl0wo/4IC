package verficatpsit;

/**
 *
 * @author Florian
 */
public class Pila1<T> {

    private Nodo1<T> testa;
    private int lunghezza;

    public Pila1() {
        this.testa = new Nodo1<>(null);
    }

    public void push(T x) {
        Nodo1 nuovaTesta = new Nodo1(x);
        if(testa==null){
            testa = nuovaTesta;
        }else{
            nuovaTesta.setSotto(testa);
            testa = nuovaTesta;
        }
        lunghezza++;
    }
    
    public Nodo1 pop() {
        Nodo1 testAttuale = null;
        if(lunghezza>=0){
            testAttuale = testa;
            testa = testa.getSotto();
            lunghezza--;
        }else{
            System.out.println("La pila é vuota");
        }
        return testAttuale;
    }

    public T getFront() {
        return this.testa.getValore();
    }

    public void visita(){
        System.out.println("VISITA : ");
        testa.visita(this.lunghezza);
    }
    
    public int getLunghezza() {
        return lunghezza;
    }

    public Pila1<T> clona() {
        Pila1<T> pilaClonata = new Pila1<>();
        int nElementi = this.lunghezza;
        Nodo1 aCapo = testa;
        for (int i = 0; i < nElementi; i++) {
            pilaClonata.push((T) aCapo.getValore());
            aCapo = (Nodo1) aCapo.getSotto();
        }
        return pilaClonata;
    }
    
    public void rovescia(){
        Pila1<T> pilaClonata = this.clona();
        
    }
    
}

class Nodo1<T> {

    private T valore;
    private Nodo1<T> sotto;

    public Nodo1(T valore) {
        this.valore = valore;
    }

    public T getValore() {
        return valore;
    }

    public Nodo1<T> getSotto() {
        return sotto;
    }

    public void setValore(T valore) {
        this.valore = valore;
    }

    public void visita(int contatore){
        System.out.println(valore);
        if(contatore>1)
            sotto.visita(contatore-1);
    }
    
    public void setSotto(Nodo1<T> sopra) {
        this.sotto = sopra;
    }
    
}
