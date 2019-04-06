//Edges that are used to bind vertices.
//This is a component of "Directed Weighted Graph" implementation.

package Graph;

public class Edge<T> {

	private Node<T> from;

	private Node<T> to;

	private int weight;

	public Edge(Node<T> node1, Node<T> node2) {

		this(node1, node2, 1);
	}

	public Edge(Node<T> node1, Node<T> node2, int weight) {

		this.from = node1;
		this.to = node2;
		this.weight = weight;
	}

	public Node<T> fromNode() {
		return from;
	}

	public Node<T> toNode() {
		return to;
	}

	public int weight() { return weight; }

	public boolean isBetween(Node<T> node1, Node<T> node2) {
		return (this.from == node1 && this.to == node2);
	}
}
