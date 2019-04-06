import java.util.BitSet;

public class BitMatrix {

	private BitSet[] sets;

	public BitMatrix(int rows, int cols) {

		this.sets = new BitSet[rows];
		for(int i=0; i < rows; i++) {
			sets[i] = new BitSet(cols);
		}
	}

	public void flush() {

		int rows = sets.length;
		int cols = sets[0].size();
		sets = new BitSet[rows];
		for (int i = 0; i < rows; i++)
			sets[i] = new BitSet(cols);       
	}

	public void clear(int r) {

		sets[r].clear();
	}

	public boolean get(int row, int col) {

		return sets[row].get(col);
	} 

	public void set(int row, int col) {

		sets[row].set(col);
	}
	
	public void not(int row) {
		
		sets[row].flip(0, sets[row].length());
	}

	public void or(int r1, int r2) {
		
		sets[r1].or(sets[r2]);
	}    
	
	public void and(int r1, int r2) {
		
		sets[r1].and(sets[r2]);
	}    
	
	public void xor(int r1, int r2) {
		
		sets[r1].xor(sets[r2]);
	}       
	
	public void print()
    {
        System.out.println("\nBit Matrix : ");
        for (BitSet b : sets)
               System.out.println(b);
        System.out.println();
    }    




}
