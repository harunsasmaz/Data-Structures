
public class SplayTree {

	class SplayNode {

		SplayNode left, right, parent;
		int element;

		public SplayNode() {
			this(0, null, null, null);
		}          

		public SplayNode(int e) {
			this(e, null, null, null);
		} 

		public SplayNode(int e, SplayNode left, SplayNode right, SplayNode parent) {
			this.left = left;
			this.right = right;
			this.parent = parent;
			this.element = e;         
		}    
	}

	private SplayNode root;
	private int size = 0;

	public SplayTree() {
		root = null;
	}

	public boolean isEmpty() { return root == null; }
	public void clear() { root = null; size = 0; }

	public void makeLeftChildParent(SplayNode c, SplayNode p)
	{
		if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
			throw new NullPointerException("WRONG");

		if (p.parent != null)
		{
			if (p == p.parent.left)
				p.parent.left = c;
			else 
				p.parent.right = c;
		}
		if (c.right != null)
			c.right.parent = p;

		c.parent = p.parent;
		p.parent = c;
		p.left = c.right;
		c.right = p;
	}

	public void makeRightChildParent(SplayNode c, SplayNode p) 
	{
		if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
			throw new NullPointerException("WRONG");

		if (p.parent != null)
		{
			if (p == p.parent.left)
				p.parent.left = c;
			else
				p.parent.right = c;
		}
		if (c.left != null)
			c.left.parent = p;

		c.parent = p.parent;
		p.parent = c;
		p.right = c.left;
		c.left = p;
	}

	private void Splay(SplayNode x)
	{
		while (x.parent != null)
		{
			SplayNode Parent = x.parent;
			SplayNode GrandParent = Parent.parent;
			if (GrandParent == null)
			{
				if (x == Parent.left)
					makeLeftChildParent(x, Parent);
				else
					makeRightChildParent(x, Parent);                 
			} 
			else
			{
				if (x == Parent.left)
				{
					if (Parent == GrandParent.left)
					{
						makeLeftChildParent(Parent, GrandParent);
						makeLeftChildParent(x, Parent);
					}
					else 
					{
						makeLeftChildParent(x, x.parent);
						makeRightChildParent(x, x.parent);
					}
				}
				else 
				{
					if (Parent == GrandParent.left)
					{
						makeRightChildParent(x, x.parent);
						makeLeftChildParent(x, x.parent);
					} 
					else 
					{
						makeRightChildParent(Parent, GrandParent);
						makeRightChildParent(x, Parent);
					}
				}
			}
		}
		root = x;
	}


	public void insert(int ele) {

		SplayNode z = root;
		SplayNode p = null;

		while (z != null)
		{
			p = z;
			if (ele > p.element)
				z = z.right;
			else
				z = z.left;
		}

		z = new SplayNode();
		z.element = ele;
		z.parent = p;

		if (p == null)
			root = z;
		else if (ele > p.element)
			p.right = z;
		else
			p.left = z;

		Splay(z);
		size++;
	}


	private void remove(SplayNode node) {
		if (node == null)
			return;

		Splay(node);

		if( (node.left != null) && (node.right !=null)) { 

			SplayNode min = node.left;
			while(min.right!=null)
				min = min.right;

			min.right = node.right;
			node.right.parent = min;
			node.left.parent = null;
			root = node.left;

		} else if (node.right != null) {

			node.right.parent = null;
			root = node.right;

		} else if( node.left !=null) {

			node.left.parent = null;
			root = node.left;

		} else {
			root = null;
		}

		node.parent = null;
		node.left = null;
		node.right = null;
		node = null;
		size--;
	}

	public void remove(int e) {

		SplayNode node = find(e);
		remove(node);
	}
	
	public boolean contains(int val) {
		
		return find(val) != null;
		
	}

	private SplayNode find(int e)
	{
		SplayNode PrevNode = null;
		SplayNode z = root;
		
		while (z != null)
		{
			PrevNode = z;
			if (e > z.element)
				z = z.right;
			else if (e < z.element)
				z = z.left;
			else if(e == z.element) {
				Splay(z);
				return z;
			}
		}

		if(PrevNode != null) {

			Splay(PrevNode);
			return null;
		}
		return null;
	}
	
	public void inorder(SplayNode r) {
		
		if (r != null) {
			inorder(r.left);
			System.out.println(r.element + ", ");
			inorder(r.right);
		}
	}
	
	public void print() {
		System.out.println("[");
		inorder(root);
		System.out.println("]");
	}









}
