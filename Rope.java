
public class Rope {

	class RopeNode {

		RopeNode left,right;
		String data;
		int weight;

		RopeNode(String data){
			this.data = data;
			this.left = null;
			this.right = null;
			this.weight = data.length();
		}

		RopeNode(){
			this.data = null;
			this.left = null;
			this.right = null;
			this.weight = 0;
		}
	}

	RopeNode root;

	public Rope() {
		root = new RopeNode("");
	}

	public void makeEmpty() { root = new RopeNode(""); }

	public void concat(String str) {

		RopeNode r = new RopeNode(str);
		RopeNode newRoot = new RopeNode();
		newRoot.left = root;
		newRoot.right = r;
		newRoot.weight = newRoot.left.weight;

		if (newRoot.left.right != null)
			newRoot.weight += newRoot.left.right.weight;

		root = newRoot;
	}

	public char indexAt(int ind) {

		RopeNode tmp = root;

		if (ind > tmp.weight) {

			ind -= tmp.weight;
			return tmp.right.data.charAt(ind);
		}

		while (ind < tmp.weight)
			tmp = tmp.left;

		ind -= tmp.weight;
		return tmp.right.data.charAt(ind);            
	}

	public String substring(int start, int end) {

		String str = "";
		boolean found = false;
		RopeNode tmp = root;

		if (end > tmp.weight) {

			found = true;
			end -= tmp.weight;
			if (start > tmp.weight)
			{
				start -= tmp.weight;
				str = tmp.right.data.substring(start, end);
				return str;
			}
			else
				str = tmp.right.data.substring(0, end);            
		}

		if (!found) {

			while (end <= tmp.weight)
				tmp = tmp.left;

			end -= tmp.weight;

			if (start >= tmp.weight) {

				start -= tmp.weight;
				str = tmp.right.data.substring(start, end) + str;
				return str;
			}

			str = tmp.right.data.substring(0, end);            
		}   

		tmp = tmp.left;

		while (start < tmp.weight)
		{
			str = tmp.right.data + str;
			tmp = tmp.left;
		}

		start -= tmp.weight;
		str = tmp.right.data.substring(start) + str;    

		return str;        
	}

	public void print() { print(root); }

	private void print(RopeNode r) {

		if (r != null) {

			print(r.left);

			if (r.data != null)
				System.out.print(r.data);

			print(r.right);
		}
	}    


}
