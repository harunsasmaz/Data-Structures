
public class SkipList {

	class Node{

		int element;
		Node down;
		Node right;

		public Node(int x, Node r, Node d) {
			this.element = x;
			this.right = r;
			this.down = d;
		}

		public Node(int x) {
			this(x, null, null);
		}





	}

	private Node head;
	private int limit;
	private Node bottom;
	private Node tail;
	
	public SkipList(int lim)
	{
		this.limit = lim;
		this.bottom = new Node(0);
		this.bottom.right = bottom.down = bottom;
		this.tail = new Node(limit);
		this.tail.right = tail;
		this.head = new Node(limit, tail, bottom);
	}


	public void insert(int e) {

		Node current = head;
		bottom.element = e;
		while(current.element < e) {
			current = current.right;

			if (current.down.right.right.element < current.element)
			{
				current.right = new Node(current.element, current.right, current.down.right.right);
				current.element = current.down.right.element;
			}
			else {
				current = current.down;
			}
		}

		if (head.right != tail)
			head = new Node(limit, tail, head);

	}
	
	public Node search(int value){
	    Node start = head;

	    while(start!=null && start.element != value ){

	        if(start.right.element < value){
	            start = start.right;
	        }else{
	            start = start.down;
	        }

	    }
	    return start;
	}
	
	public boolean contains(int value) {
		return search(value) != null ? true : false;
	}
	
	

	public void clear() {
		head.right = tail;
		head.down = bottom;
	}
	
	
	public boolean isEmpty() {
        return head.right == tail && head.down == bottom;
    }
	
	
	
	
	public void printList()
    {
        System.out.print("\n * ");
        Node current = head;
        while( current.down != bottom )
            current = current.down;
        while (current.right != tail )
        {
            System.out.print(current.element +" ");
            current = current.right;
        }
        System.out.println();
    }   
	








}
