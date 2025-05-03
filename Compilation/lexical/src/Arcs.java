public class Arcs {
	int destination;
	String contenue;
	Arcs next;

	Arcs (int destination, String contenue, Arcs next) {
		this.destination = destination;
		this.contenue = contenue;
		this.next = next;
	}
}
