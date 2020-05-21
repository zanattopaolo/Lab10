package it.polito.tdp.bar.model;

public class Statistiche {
	
	private int clientiTot;
	private int clientiSoddisfatti;
	private int clientiInsoddisfatti;
	
	public Statistiche() {
		this.clientiTot=0;
		this.clientiSoddisfatti=0;
		this.clientiInsoddisfatti=0;
	}
	
	public int getClientiTot() {
		return clientiTot;
	}
	public void addClientiTot() {
		this.clientiTot++;
	}
	public int getClientiSoddisfatti() {
		return clientiSoddisfatti;
	}
	public void addClientiSoddisfatti() {
		this.clientiSoddisfatti++;
	}
	public int getClientiInsoddisfatti() {
		return clientiInsoddisfatti;
	}
	public void addClientiInsoddisfatti() {
		this.clientiInsoddisfatti++;
	}
	
	public void clear() {
		this.clientiTot = 0;
		this.clientiSoddisfatti = 0;
		this.clientiInsoddisfatti = 0;
	}
	
	

}