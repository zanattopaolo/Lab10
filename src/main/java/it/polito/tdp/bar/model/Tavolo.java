package it.polito.tdp.bar.model;

public class Tavolo implements Comparable<Tavolo>{
	
	private int capienza;
	private boolean libero;
	
	public Tavolo() {
		this.capienza = -1;
		this.libero = false;
	}
	
	public Tavolo(int capienza, boolean libero) {
		this.capienza = capienza;
		this.libero = libero;
	}

	public boolean isLibero() {
		return libero;
	}
	
	public void setLibero(boolean libero) {
		this.libero = libero;
	}

	public int getCapienza() {
		return capienza;
	}

	@Override
	public int compareTo(Tavolo other) {
		int c1 = this.capienza;
		int c2 = other.getCapienza();
		
		if(c1<c2)
			return -1;
		else {
			if(c1>c2)
				return +1;
			else
				return 0;
		}
	}

	@Override
	public String toString() {
		return "Tavolo [capienza=" + capienza + ", libero=" + libero + "]";
	}
	
	
	
	

}
