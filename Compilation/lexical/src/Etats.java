public class Etats {
	int etat_ID;
	boolean is_Final;
	String tag;
	Etats next;
	Arcs arc;
	
	Etats (int etat_ID, boolean is_Final, String tag, Etats next, Arcs arc) {
		this.etat_ID = etat_ID;
		this.is_Final = is_Final;
		this.next = next;
		this.arc = arc;
		this.tag = tag;
	}
	
	Etats (int etat_ID, Etats next, Arcs arc) {
		this.etat_ID = etat_ID;
		this.is_Final = false;
		this.next = next;
		this.arc = arc;
		this.tag = "";
	}
}
