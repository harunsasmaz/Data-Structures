import java.security.*;
import java.math.*;
import java.nio.*;

public class BloomFilter
{    
	private byte[] set;
	private int keySize, capacity, size;
	private MessageDigest md;


	public BloomFilter(int capacity, int kSize) {
		
		this.capacity = capacity;
		this.set = new byte[capacity];
		this.keySize = kSize;
		this.size = 0;
		try 
		{
			this.md = MessageDigest.getInstance("MD5");
		} 
		catch (NoSuchAlgorithmException e) 
		{
			throw new IllegalArgumentException("Error : MD5 Hash not found");
		}
	}

	public void makeEmpty() {
		
		set = new byte[capacity];
		size = 0;
		try 
		{
			md = MessageDigest.getInstance("MD5");
		} 
		catch (NoSuchAlgorithmException e) 
		{
			throw new IllegalArgumentException("Error : MD5 Hash not found");
		}
	}

	public boolean isEmpty() {	return size == 0; }

	public int getSize() {	return size; }

	private int getHash(int i) {
		
		md.reset();
		byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();
		md.update(bytes, 0, bytes.length);
		return Math.abs(new BigInteger(1, md.digest()).intValue()) % (set.length - 1);
	}

	public void insert(Object obj) {
		
		int[] tmpset = getHashArray(obj);
		for (int i : tmpset)
			set[i] = 1;
		size++;
	}

	public boolean search(Object obj) {
		
		int[] tmpset = getHashArray(obj);
		for (int i : tmpset)
			if (set[i] != 1)
				return false;
		return true;
	}

	private int[] getHashArray(Object obj) {
		
		int[] tmpset = new int[keySize];
		tmpset[0] = getHash(obj.hashCode());
		for (int i = 1; i < keySize; i++)
			tmpset[i] = (getHash(tmpset[i - 1]));
		return tmpset;
	}    
}

