/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdatadownload;

/**
 *
 * @author david.ambros
 */
public interface Osservabile {
	public void registraOsservatore(Osservatore osservatore);
    
    public void notificaOsservatore();
}
