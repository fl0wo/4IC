/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.util.concurrent.Semaphore;

/**
 *
 * @author FLORIAN.SABANI
 */
public class Risorsa {

	protected Semaphore s;
	private String valoreRisorsaPreziosissima;
	protected boolean isOccupata;

	public Risorsa(String risorsaPreziosa) {
		this.valoreRisorsaPreziosissima = risorsaPreziosa;
		s = new Semaphore(1);
	}

	public String getRisorsaPreziosa() {
		this.setIsOccupata(true);
		return valoreRisorsaPreziosissima;
	}

	public void rilasciaRisorsa() {
		this.setIsOccupata(false);
	}

	public void setValoreRisorsaPreziosissima(String valoreRisorsaPreziosissima) {
		this.valoreRisorsaPreziosissima = valoreRisorsaPreziosissima;
	}

	public void setIsOccupata(boolean isOccupata) {
		this.isOccupata = isOccupata;
	}

	public boolean getIsOccupata() {
		return isOccupata;
	}
}
