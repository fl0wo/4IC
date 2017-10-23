package threadsort;

import java.util.Arrays;
/**
 *
 * @author Florian
 */
public class ThreadSort {
    // Per ordinare ho usato il bubble sort :P
    public static void main(String[] args) throws InterruptedException{
        
        OrdinamentoParallelo o =  new OrdinamentoParallelo();
        
        int min =0;
        int max = 100;
        int nElementi = 100;
        int[] array = o.randomArray(nElementi,min,max);
        
        System.out.println("Il metodo di ordinamento usato é : "+o.CRITERIO_ORDINAMENTO);
        
        int[] daOrdinareCoiThread = array.clone();
        System.out.println(Arrays.toString(array));
        
        long tempoAttuale = System.currentTimeMillis();
        int nThread = 4;
        o.ordinaParallelo(daOrdinareCoiThread,nThread);
        System.out.println("Ci ha messo : "+(System.currentTimeMillis()-tempoAttuale)+"ms a ordinare "+nElementi+" elementi con "+nThread+" Thread!");
        
    }
    
}
