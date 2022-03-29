// this class implements the directed graph of the bus routes between stops
public class WeightedEdge {
	private int from;
	private int to;
	private double weight;
	
	WeightedEdge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this .weight = weight;
	}
	
	public double weight() {
		return weight;
	}
	
	public int to() {
		return to;
	}
	
	public int from() {
		return from;
	}
	
	

}
