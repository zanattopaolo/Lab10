package it.polito.tdp.bar.model;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {
	
	private PriorityQueue<Event> eventi;
	private Statistiche stats;
	List<Tavolo> tavoli;
//	private Duration tempo; 	//riferimento temporale del simulatore
	private int tempoDiOccupazione;
	private int distanzaEventi;
	private int numEventi;
	private int maxNumPersone;
	
	
	
	public Simulator() {
		this.eventi = new PriorityQueue<Event>();
		this.stats = new Statistiche();
		this.tavoli = new LinkedList<Tavolo>();
//		this.tempo = Duration.of(0, ChronoUnit.MINUTES);
		
		this.tempoDiOccupazione = 10;
		this.distanzaEventi=10;
		this.numEventi = 10;
		this.maxNumPersone = 10;
	}
	
	//setParameter
	public void setTempoDiOccupazione(int t) {
		this.tempoDiOccupazione=t;
	}
	
	public void setDistanzaEventi(int t) {
		this.distanzaEventi=t;
	}
	
	public void setNumEventi(int n) {
		this.numEventi=n;
	}
	
	public void setMaxNumPersone(int maxNumPersone) {
		this.maxNumPersone = maxNumPersone;
	}
	
	
	/**
	 * Metodo di simulazione
	 * @return stringa contenente gli esiti
	 */
	public String run() {
		
		//PREPARAZIONE DEL WORLD STATUS
		this.eventi.clear();
		this.stats.clear();
		
		this.tavoli.add(new Tavolo(4, true));
		this.tavoli.add(new Tavolo(4, true));
		this.tavoli.add(new Tavolo(4, true));
		this.tavoli.add(new Tavolo(4, true));
		this.tavoli.add(new Tavolo(4, true));
		this.tavoli.add(new Tavolo(6, true));
		this.tavoli.add(new Tavolo(6, true));
		this.tavoli.add(new Tavolo(6, true));
		this.tavoli.add(new Tavolo(6, true));
		this.tavoli.add(new Tavolo(8, true));
		this.tavoli.add(new Tavolo(8, true));
		this.tavoli.add(new Tavolo(8, true));
		this.tavoli.add(new Tavolo(8, true));
		this.tavoli.add(new Tavolo(10, true));
		this.tavoli.add(new Tavolo(10, true));
		
		//CARICAMENTO DELLA CODA
		this.loadQueue();
		
		Event e;
		while(!this.eventi.isEmpty()) {
			e=eventi.poll();
			this.processEvent(e);
			System.out.println(e.toString());
		}
		
		System.out.println("\n\nNumero clienti totali: "+this.stats.getClientiTot());
		System.out.println("Numero clienti soddisfatti: "+this.stats.getClientiSoddisfatti());
		System.out.println("Numero clienti insoddisfatti: "+this.stats.getClientiInsoddisfatti());
		
		return "Numero clienti totali: "+this.stats.getClientiTot()+"\nNumero clienti soddisfatti: "+this.stats.getClientiSoddisfatti()+"\nNumero clienti insoddisfatti: "+this.stats.getClientiInsoddisfatti();
		
	}
	

/**
 * Metodo di caricamento della coda
 */
	private void loadQueue() {
		
		int tempo = 0; 	//riferimento temporale
		
		int numPersone;
		int durata, distanzaProssimoCliente;
		float tolleranza;
		GruppoClienti g;
		
		for(int i=0; i<this.numEventi; i++) {
			
			numPersone = (int) (1+Math.random()*(this.maxNumPersone-1));
			durata = (int) (this.tempoDiOccupazione +  Math.random()*this.tempoDiOccupazione);
			tolleranza = (float) (Math.random()*0.9);
			
			g=new GruppoClienti(numPersone, durata, tolleranza);
			
			Event e = new Event(tempo, EventType.ARRIVO_GRUPPO_CLIENTI, g);
			this.eventi.add(e);
			
			distanzaProssimoCliente = (int) (1+Math.random()*(this.distanzaEventi-1));
			tempo=tempo+distanzaProssimoCliente;
		}
	}
	
	/**
	 * Metodo di escuzione dell'evento
	 * @param e evento da eseguire
	 */
	private void processEvent(Event e) {
		switch(e.getTipo()) {
		case ARRIVO_GRUPPO_CLIENTI:
			float disponibilita;	//disponibilita' randomica dell'evento per andare al bancone piuttosto che stare al tavolo
			disponibilita= (float)(Math.random());
			Tavolo t = this.getBestTavolo(e.getClienti().getNumPersone());
			
			if(disponibilita>e.getClienti().getTolleranza()) {		//se non sono disponibili
				if(t==null) {		//se non c'è posto
					//AGGIORNAMENTO MISURAZIONI
					this.stats.addClientiTot();
					this.stats.addClientiInsoddisfatti();

					//NON C'E' GENERAZIONE DI NUOVI EVENTI
					//AGGIONAMENTO VALORI WOLRD STATUS NON E' RICHIESTA
				}
				else {					
					//GENERAZIONE NUOVI EVENTI
					Event restituzione = new Event(e.getTime()+(e.getClienti().getDurata()), EventType.LIBERAZIONE_TAVOLO, e.getClienti());
					this.eventi.add(restituzione);
					
					//AGGIORNAMENTO MISURAZIONI
					this.stats.addClientiTot();
					this.stats.addClientiSoddisfatti();
					
					//AGGIONAMENTO VALORI WOLRD STATUS DENTRO getBestTavolo() +
					e.getClienti().setTavolo(t);
				}
			}
			else {				//se sono disponibili
				if(t==null) {		//se non c'è posto
					//AGGIORNAMENTO MISURAZIONI
					this.stats.addClientiTot();
					this.stats.addClientiSoddisfatti();
					
					//NON C'E' GENERAZIONE DI NUOVI EVENTI
					//AGGIONAMENTO VALORI WOLRD STATUS NON E' RICHIESTA
				}
				else {					
					//GENERAZIONE NUOVI EVENTI
					Event restituzione = new Event(e.getTime()+(e.getClienti().getDurata()), EventType.LIBERAZIONE_TAVOLO, e.getClienti());
					this.eventi.add(restituzione);
					
					//AGGIORNAMENTO MISURAZIONI
					this.stats.addClientiTot();
					this.stats.addClientiSoddisfatti();
					
					//AGGIONAMENTO VALORI WOLRD STATUS DENTRO getBestTavolo() +
					e.getClienti().setTavolo(t);
				}
				
			}
		break;
		case LIBERAZIONE_TAVOLO:
			//NON CI SONO AGGIORNAMENTI DI MISURAZIONI
			//NON C'E' GENERAZIONE DI NUOVI EVENTI
			//AGGIONAMENTO VALORI WOLRD STATUS
			Tavolo t2 = e.getClienti().getTavolo();
			t2.setLibero(true);
		break;

		}
		
	}
	
	/**
	 * Metodo per la gestione dei tavoli
	 * @param n numero di clienti che richiedono il tavolo
	 * @return null se non c'è nessun tavolo disponibile; senno' il tavolo
	 */
	private Tavolo getBestTavolo(int n) {
		
		for(Tavolo t:this.tavoli) {
			if(t.isLibero() && n<=t.getCapienza() && n>=t.getCapienza()*0.5) {
				t.setLibero(false);
				return t;
			}
		}
		
		return null;
	}
	
	
	
	
}
