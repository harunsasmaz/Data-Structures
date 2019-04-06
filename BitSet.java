
public class BitSet {

	private byte[] bits;
	private int size;

	public BitSet(int size) {
		this.size = size;
		this.bits = new byte[size/8 + 1];
	}

	public BitSet(BitSet copy) {
		this.size = copy.size;
		bits = new byte[copy.bits.length];
		System.arraycopy(copy.bits, 0, this.bits, 0, copy.bits.length);
	}
	
	public int size() { return size; }

	public void clear() {

		bits = new byte[bits.length];
	}

	public void setBit(int n) {

		bits[n / 8] |= (1 << (n % 8));
	}

	public boolean getBit(int n) {
		return ((bits[n / 8] & (1 << (n % 8))) != 0);
	}

	public void clearBit(int n) {

		bits[n / 8] &= ((1 << (n % 8)) ^ ((1 << 8) - 1));
	}


	public void or(BitSet second) {

		for (int i = 0; i < bits.length; i++) {

			if (i < second.bits.length)
				bits[i] |= second.bits[i];
			else
				bits[i] = 0;
		}
	}

	public void and(BitSet second) {

		for (int i = 0; i < bits.length; i++) {

			if (i < second.bits.length)
				bits[i] &= second.bits[i];
			else
				bits[i] = 0;
		}
	}

	public void display()
	{
		System.out.print("\nBit Set : ");
		for (int i = 0; i < size; i++)
			if (getBit(i))
				System.out.print(i +" ");
		System.out.println();
	}    

}


















