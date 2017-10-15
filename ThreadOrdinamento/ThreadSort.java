/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsort;

import java.util.Arrays;

/**
 *
 * @author Florian
 */
public class ThreadSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException{
        OrdinamentoParallelo o =  new OrdinamentoParallelo();
        int[] array = o.randomArray(50,0,100);
        //System.out.println(Arrays.toString(array));
        o.ordinaParallelo(array,10);
    }
    
}
