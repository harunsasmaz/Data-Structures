//Singly Linked List
public class SLinkedList<K> {
	
	public class Node<E>{
		
		private E element;
		private Node<E> nextNode;
		
		public Node(E e, Node<E> n) {
			this.element = e;
			this.nextNode = n;
		}
		
		public Node() {
			this(null, null);
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
		
		
		
	}
	
	private Node<K> headNode;
	private Node<K> tailNode;
	private int size;
	
	public SLinkedList() {
		this.headNode = new Node<K>(null,null);
		this.tailNode = new Node<K>(null,null);
		this.size = 0;
	}
	
	public int size() { return size; }
	public boolean isEmpty() { return size==0 ? true : false; } 
	public Node<K> getHead() { return headNode; }
	
	public void insert(K e) {
		
		if(size == 0) {
			headNode.setElement(e);
	
		} else if(size == 1) {
			headNode.setNextNode(tailNode);
			tailNode.setElement(e);
		} else {
			Node<K> add = new Node<K>(e,null);
			tailNode.setNextNode(add);
			tailNode = add;
		}
		size++;
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
