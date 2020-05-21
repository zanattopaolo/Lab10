package it.polito.tdp.bar.model;



public class Event implements Comparable<Event>{
	
	public enum EventType {
		ARRIVO_GRUPPO_CLIENTI, LIBERAZIONE_TAVOLO
	}
	
	private int time;		//identificativo temporale
	private EventType tipo;		//tipo di evento
	private GruppoClienti clienti;	//oggetto GruppoClienti
	
	public Event(int time, EventType tipo, GruppoClienti clienti) {
		this.time = time;
		this.tipo = tipo;
		this.clienti = clienti;
	}

	public int getTime() {
		return time;
	}


	public EventType getTipo() {
		return tipo;
	}

	public GruppoClienti getClienti() {
		return clienti;
	}

	@Override
	public int compareTo(Event other) {
		int t1=this.time;
		int t2=other.getTime();
		
		if(t1<t2)
			return -1;
		else {
			if(t1>t2)
				return +1;
			else
				return 0;
		}
	}

	@Override
	public String toString() {
		return "Event [" + time + ", " + tipo + ", " + clienti + "]";
	}
	

}
