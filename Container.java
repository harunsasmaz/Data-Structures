
public class Container<K> {
	
	private DLinkedList<K> list;
	private final String type;
	
	public Container(String type) {
		this.type = type;
		this.list = new DLinkedList<K>();
	}
	
	public int size() { return list.size(); }
	public boolean isEmpty() { return list.isEmpty(); }
	
	public void push(K e) { 
		
		if(type.equalsIgnoreCase("Stack")) 
			list.insertFront(e);
		else 
			list.insertBehind(e);
		
	}
	public K pop() { return list.removeFront(); }
	public K top() { return list.getHead().getElement(); }
	
	
	
}
