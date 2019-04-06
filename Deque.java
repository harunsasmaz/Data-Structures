

public class Deque<T> {

	private T[] buckets;
	private int head;
	private int size;

	public Deque() {
		this.head = 0;
		this.size = 0;
		resize();
	}

	@SuppressWarnings("unchecked")
	public void resize() {

		T[] temp = (T[]) new Object[Math.max(2*size, 1)];
		for (int k = 0; k < size; k++) 
			temp[k] = buckets[(head + k) % buckets.length];
		buckets = temp;
		head = 0;
	}

	public int size() { return size; }
	public boolean isEmpty() { return size == 0; }

	public T get(int i) {

		return buckets[(head + i) % buckets.length];
	}

	public T set(int i, T x) {
		T y = buckets[(head + i) % buckets.length];
		buckets[(head + i) % buckets.length] = x;
		return y;
	}

	public void addFront(T x) {
		add(size, x);
	}

	public void addTail(T x) {
		add(head, x);
	}

	public void add(int i, T x) 
	{
		if (size + 1 > buckets.length)
			resize();

		if (i < size/2) 
		{ 
			/** shift left one position **/
			head = (head == 0) ? buckets.length - 1 : head - 1;

			for (int k = 0; k <= i - 1; k++)
				buckets[(head + k) % buckets.length] = buckets[(head + k + 1)%buckets.length];
		} 
		else 
		{ 
			/** shift right one position **/
			for (int k = size; k > i; k--)
				buckets[(head + k) % buckets.length] = buckets[(head + k - 1)%buckets.length];
		}
		buckets[(head + i) % buckets.length] = x;
		size++;
	}

	public T remove(int i) {

		T x = buckets[(head + i) % buckets.length];
		if (i < size/2) {

			for (int k = i; k > 0; k--)
				buckets[(head + k) % buckets.length] = buckets[(head + k - 1) % buckets.length];

			head = (head + 1) % buckets.length;

		} else {

			for (int k = i; k < size - 1; k++)
				buckets[(head + k) % buckets.length] = buckets[(head + k + 1) % buckets.length];
		}

		size--;
		if (3 * size < buckets.length) 
			resize();

		return x;
	}


	public void print() {

		System.out.print("\n * ");
		int p = head;

		for (int i = 0; i < size; i++) {

			System.out.print(buckets[p % buckets.length] +" ");
			p++;
		}
		System.out.println();
	}    
}


