package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class GruppoClienti {
	
	private int numPersone;
	private int durata;
	private float tolleranza;
	private Tavolo tavolo;
	
		
	public GruppoClienti(int numPersone, int durata, float tolleranza) {
		this.numPersone = numPersone;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.tavolo = new Tavolo();
	}


	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	public int getNumPersone() {
		return numPersone;
	}

	public int getDurata() {
		return durata;
	}

	public float getTolleranza() {
		return tolleranza;
	}

	@Override
	public String toString() {
		return "GruppoClienti [numPersone=" + numPersone + ", durata=" + durata + ", tolleranza=" + tolleranza
				+ ", tavolo=" + tavolo + "]";
	}
	
	
	

}
