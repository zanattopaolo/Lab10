package it.polito.tdp.bar.model;

public class Model {
	
	private Simulator s;
	
	public Model() {
		s = new Simulator();
	}
	

	
	public String eseguiSimulazione() {
		s.setDistanzaEventi(10);
		s.setMaxNumPersone(10);
		s.setNumEventi(2000);
		s.setTempoDiOccupazione(60);
		
		return s.run();
		
	}

}
