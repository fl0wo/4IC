/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfiletime;

/**
 *
 * @author florian.sabani
 */
public interface Osservatore {
	
	public void aggiorna();
	public void setDaOsservare(Osservato o);
}
