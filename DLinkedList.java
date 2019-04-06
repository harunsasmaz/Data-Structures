
//Doubly Linked List
public class DLinkedList<K> {
	
	public class Node<E>{
		
		private E element;
		private Node<E> nextNode;
		private Node<E> prevNode;
		
		public Node(E e, Node<E> p, Node<E> n) {
			this.element = e;
			this.nextNode = n;
			this.prevNode = p;
		}
		
		public Node() {
			this(null,null,null);
		}

		public E getElement() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public Node<E> getNextNode() {
			return nextNode;
		}

		public void setNextNode(Node<E> nextNode) {
			this.nextNode = nextNode;
		}
		
		public Node<E> getPrevNode(){
			return prevNode;
		}
		
		public void setPrevNode(Node<E> p) {
			this.prevNode = p;
		}
		
	}
	
	private Node<K> headNode;
	private Node<K> tailNode;
	private int size;
	
	public DLinkedList() {
		this.size = 0;
		this.headNode = new Node<K>();
		this.tailNode = new Node<K>();
	}
	
	public int size() { return size; }
	public boolean isEmpty() { return size == 0 ? true : false; }
	public Node<K> getHead() { return headNode; }
	public Node<K> getTail() { return tailNode; }	
	
	public void insertBehind(K e) {
		
		if(size == 0) {
			headNode.setElement(e);
		}
		
		else if(size == 1) {
			tailNode.setElement(e);
			tailNode.setPrevNode(headNode);
			headNode.setNextNode(tailNode);
		}
		
		else {
			Node<K> node = new Node<K>(e,tailNode,null);
			tailNode.setNextNode(node);
			tailNode = node;
		}
		
		size++;
		
	}
	
	
	public void insertFront(K e) {
		
		if(size == 0) {
			headNode.setElement(e);
		}
		
		else if(size == 1) {
			tailNode.setElement(e);
			tailNode.setPrevNode(headNode);
			headNode.setNextNode(tailNode);
		}
		
		else {
			Node<K> node = new Node<K>(e,null,headNode);
			headNode.setPrevNode(node);
			headNode = headNode.getPrevNode();
		}
		
		size++;
	}
	
	
	public K removeFront() {
		
		if(isEmpty()) return null;
		
		K old = headNode.getElement();

		if(size == 1) {
			headNode.setElement(null);
		}
		else {	
			headNode.getNextNode().setPrevNode(null);
			headNode = headNode.getNextNode();
		}
		size--;
		return old;
	}
	
	
	public K removeBehind() {
		
		if(isEmpty()) return null;
		
		K old;
		
		if(size == 1) {
			old = headNode.getElement();
			headNode.setElement(null);
		} 
		
		else {
			old = tailNode.getElement();
			tailNode.getPrevNode().setNextNode(null);
			tailNode = tailNode.getPrevNode();
		}
		
		size--;
		return old;
		
	}
	
	
	public K remove(K e) {
		
		Node<K> currentNode = headNode;
		Node<K> prevNode = null;
		K old = currentNode.getElement();
		
		while(currentNode.getElement() != e) {
			prevNode = currentNode;
			currentNode = currentNode.getNextNode();
			if(currentNode == null) break;
			old = currentNode.getElement();
		}
		
		if(old != e) {
			return null;
		} else if (size == 1) {
			headNode.setElement(null);
			size--;
			return old;
		} else {
			if(prevNode == null) {
				headNode = headNode.getNextNode();
			} else {
				prevNode.setNextNode(currentNode.getNextNode());
				if(currentNode.getNextNode() != null)
					currentNode.getNextNode().setPrevNode(prevNode);
				currentNode.setNextNode(null);
			}
			size--;
			return old;
		}
	}
	
	public int index(K e) {
		Node<K> currentNode = headNode;
		int count = 0;
		
		while(currentNode.getElement() != e) {	
			currentNode = currentNode.getNextNode();
			if(currentNode == null) break;
			count++;	
		}
		
		return currentNode != null ? count : -1 ;
		
	}
	
	
}
