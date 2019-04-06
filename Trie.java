import java.util.HashMap;

public class Trie {

	class TrieNode {

		char c;
		HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
		boolean isEnd;

		public TrieNode() {}

		public TrieNode(char c){
			this.c = c;
		}
	}

	private TrieNode root;

	public Trie() {
		this.root = new TrieNode();
	}


	public void insert(String key) {
		HashMap<Character, TrieNode> children = root.children;

		for(int i=0; i < key.length(); i++) {
			char x = key.charAt(i);

			TrieNode t;
			if(children.containsKey(x)){
				t = children.get(x);
			}else{
				t = new TrieNode(x);
				children.put(x, t);
			}

			children = t.children;

			if(i == key.length() - 1) {
				t.isEnd = true;
			}

		}
	}

	public boolean search(String key) {

		HashMap<Character, TrieNode> children = root.children; 
		TrieNode t = null;
		for(int i=0; i< key.length(); i++){
			char c = key.charAt(i);
			if(children.containsKey(c)){
				t = children.get(c);
				children = t.children;
			}else{
				return false;
			}
		}
		
		return t.isEnd && t != null ? true : false;
	}






}









