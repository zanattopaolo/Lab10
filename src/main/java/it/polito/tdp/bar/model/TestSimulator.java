package it.polito.tdp.bar.model;

public class TestSimulator {

	public static void main(String[] args) {
		Simulator s=new Simulator();
		s.setDistanzaEventi(10);
		s.setMaxNumPersone(10);
		s.setNumEventi(10);
		s.setTempoDiOccupazione(60);
		
		s.run();

	}

}
