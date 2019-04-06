
public class Stack<K> {
	
	private DLinkedList<K> list;
	
	public Stack() {
		this.list = new DLinkedList<>();
	}
	
	public int size() { return list.size(); }
	public boolean isEmpty() { return list.isEmpty(); }
	
	public void push(K e) { list.insertFront(e); }
	public K pop() { return list.removeFront(); }
	public K top() { return list.getHead().getElement(); }
	
	
}
