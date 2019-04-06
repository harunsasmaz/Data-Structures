
public class Queue<K> {
	
	private DLinkedList<K> list;
	
	public Queue() {
		list = new DLinkedList<K>();
	}
	
	public int size() { return list.size(); }
	public boolean isEmpty() { return list.isEmpty(); }
	
	public void push(K e) { list.insertBehind(e); }
	public K pop() { return list.removeFront(); }
	public K top() { return list.getHead().getElement(); }
	
	
}
