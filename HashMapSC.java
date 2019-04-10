//HashMap with Seperate Chaining Collision Handling

import java.util.Arrays;
import java.util.LinkedList;

public class HashMapSC<A,B> {

	class Entry<K,V> {

		K key;
		V value;
		int hash;

		Entry(K key, V value){
			this.key = key;
			this.value = value;
			this.hash = key.hashCode();
		}

		public boolean equals(Entry<K, V> other) {
			if(hash != other.hash) return false;
			return key.equals(other.key);
		}

		 @Override public String toString() {
			return "(" + key + ", " + value + ")";
		}

	}

	private static final int init_cap = 4;
	private static final double CriticaloadFactor = 0.75;

	private int a;
	private int b;
	private int p;
	private int N;
	private int size;

	private LinkedList<Entry<A, B>>[] table;

	private boolean isPrime(int x) {

		for(int i=2; i <= Math.sqrt(x); i++) {
			if(x % i == 0)
				return false;
		}	
		return true;
	}

	private int nextPrime(int n) {
		int hold = n;
		while(!isPrime(++hold)) {}
		return hold;
	}

	private int randomParam() {
		Random random = new Random();
		return random.nextInt(p);
	}

	private void calculateParams() {
		p = nextPrime(N);
		a = randomParam();
		b = randomParam();
	}

	private double loadFactor() { return size/N; }
	
/*	This method is useful for open adressing and probing.
 * 
 * @SuppressWarnings("unchecked")
	private void resize() {
		N *= 2;
		LinkedList<Entry<A, B>>[] temp = (LinkedList<Entry<A, B>>[]) new Object[N];
		calculateParams();
		for(LinkedList<Entry<A, B>> list : table) {
			if(list != null) {
				for(Entry<A,B> entry : list) {
					if(temp[hash(entry.key)] == null)
						temp[hash(entry.key)] = new LinkedList<Entry<A, B>>();
					temp[hash(entry.key)].add(entry);
				}
			}
		}
		table = temp;
	}
*/	

	private int hash(A key) {
		return (int)((Math.abs(a*key.hashCode() + b) % p) % N);
	}

	@SuppressWarnings("unchecked")
	public HashMapSC() {
		this.N = init_cap;
		calculateParams();
		this.table = (LinkedList<Entry<A, B>>[]) new Object[N];
		size = 0;
	}

	public int size() { return size; }

	public boolean isEmpty() { return size == 0; }
	
	public void clear() { Arrays.fill(table, null); size = 0; }

	public Entry<A, B> get(A key) {
		if(key == null) return null;
		LinkedList<Entry<A, B>> temp = table[hash(key)];
		if(temp == null) return null;
		
		for(Entry<A, B> entry : temp) {
			if(entry.key.equals(key)) return entry;
		}
		return null;
	}

	public boolean contains(A key) {return get(key) != null; }

	public B insert(A key, B value) {

	//	THIS MUST BE CHECKED WHEN APPLYING OPEN ADRESSING METHOD
	//	if(loadFactor() > CriticaloadFactor) resize();
		
		LinkedList<Entry<A, B>> temp = table[hash(key)];
		if(temp == null) {
			temp = new LinkedList<Entry<A, B>>();
			temp.add(new Entry<A,B>(key, value));
			size++;
			return null;
		} else {
			B old;
			for(Entry<A, B> entry : temp) {
				if(entry.key.equals(key)) {
					old = entry.value;
					entry.value = value;
					return old;
				}
			}
			temp.add(new Entry<A,B>(key, value));
			size++;
			return null;
		}
	}
	
	public B delete(A key) {
		
		if(key == null) return null;
		LinkedList<Entry<A, B>> temp = table[hash(key)];
		if(temp == null) return null;
		B old;
		for(Entry<A, B> entry : temp) {
			if(entry.key.equals(key)) {
				old = entry.value;
				temp.remove(entry);
				size--;
				return old;
			}
		}
		return null;
	}
	
	public void print() {
		String s = "[";
		for(LinkedList<Entry<A, B>> list : table) {
			if(list != null) {
				for(Entry<A,B> entry : list) {
					s = s + entry.toString() + " ";
				}
			}
		}
		s = s + "]";
		System.out.println(s);
	}
	

	
	
	
}
