package Graph;

import java.util.*;

public class Graph<T> {

	private Map<T, Node<T>> adjacencyList;

	public Graph() {
		adjacencyList = new HashMap<>();
	}


	public boolean addVertex(T vertex) {
		
		if (adjacencyList.containsKey(vertex)) 
			return false;
		
		adjacencyList.put(vertex, new Node<>(vertex));
		return true;
	}

	public boolean addEdge(T vertex1, T vertex2) {
		
		return addEdge(vertex1, vertex2, 1);
	}

	
	public boolean addEdge(T vertex1, T vertex2, int weight) {
		
		if (!containsVertex(vertex1) || !containsVertex(vertex2)) 
			throw new RuntimeException("Vertex does not exist");


		Node<T> from = getNode(vertex1);
		Node<T> to = getNode(vertex2);
		return from.addEdge(to, weight);
	}

	
	public boolean removeVertex(T vertex) {
		if (!adjacencyList.containsKey(vertex)) {
			return false;
		}

		final Node<T> toRemove = getNode(vertex);

		adjacencyList.values().forEach(node -> node.removeEdge(toRemove));

		adjacencyList.remove(vertex);
		return true;
	}

	
	public boolean removeEdge(T vertex1, T vertex2) {
		
		if (!containsVertex(vertex1) || !containsVertex(vertex2))
			return false;
		
		return getNode(vertex1).removeEdge(getNode(vertex2));
	}

	public int vertexCount() {
		return adjacencyList.keySet().size();
	}

	
	public int edgeCount() {
		return adjacencyList.values()
				.stream()
				.mapToInt(Node::getEdgeCount)
				.sum();
	}

	
	public boolean containsVertex(T vertex) {
		return adjacencyList.containsKey(vertex);
	}

	
	public boolean containsEdge(T vertex1, T vertex2) {
		
		if (!containsVertex(vertex1) || !containsVertex(vertex2)) 
			return false;
		
		return getNode(vertex1).hasEdge(getNode(vertex2));
	}

	
	public List<T> shortestPath(T startVertex, T endVertex) {
		// if nodes not found, return empty path
		if (!containsVertex(startVertex) || !containsVertex(endVertex)) 
			return null;
		
		// run bfs on the graph
		runBFS(startVertex);

		List<T> path = new ArrayList<>();
		// trace path back from end vertex to start
		Node<T> end = getNode(endVertex);
		while (end != null && end != getNode(startVertex)) {
			path.add(end.vertex());
			end = end.parent();
		}
		// if end is null, node not found
		if (end == null) {
			return null;
		}
		else {
			Collections.reverse(path);
		}
		return path;
	}

	private void runBFS(T startVertex) {
		if (!containsVertex(startVertex)) {
			throw new RuntimeException("Vertex does not exist.");
		}

		// reset the graph
		resetGraph();

		// init the queue
		Queue<Node<T>> queue = new LinkedList<>();
		Node<T> start = getNode(startVertex);
		queue.add(start);

		// explore the graph
		while (!queue.isEmpty()) {
			Node<T> first = queue.remove();
			first.setVisited(true);
			first.edges().forEach(edge -> {
				Node<T> neighbour = edge.toNode();
				if (!neighbour.isVisited()) {
					neighbour.setParent(first);
					queue.add(neighbour);
				}
			});
		}
	}

	private Node<T> getNode(T value) {
		return adjacencyList.get(value);
	}

	private void resetGraph() {
		
		adjacencyList.keySet().forEach(key -> {
			Node<T> node = getNode(key);
			node.setParent(null);
			node.setVisited(false);
		});
	}
	
}
