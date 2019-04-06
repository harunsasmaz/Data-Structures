
public class Vector {

	private int size;
	private double[] elements;

	public Vector(int size) {   
		this.size = size;
		this.elements = new double[size];
	}

	public Vector(double[] copy) {

		this.size = copy.length;
		this.elements = new double[size];
		for(int i=0; i < size; i++) {
			elements[i] = copy[i];
		}
	}

	public int size() { return size; }

	public double dotProduct(Vector second) {

		if(this.size != second.size)
			throw new IllegalArgumentException("Sizes are not same!");

		double result = 0.0;
		for(int i=0; i<size; i++) {
			result += (this.elements[i]*second.elements[i]);
		}
		return result;
	}

	public double length() {

		return Math.sqrt(this.dotProduct(this));	
	}

	public Vector minus(Vector second) {

		if(this.size != second.size)
			throw new IllegalArgumentException("Sizes are not same!");

		Vector result = new Vector(this.size);
		for(int i=0; i < size; i++) {
			result.elements[i] = this.elements[i] - second.elements[i];
		}
		return result;
	}

	public Vector plus(Vector second) {

		if(this.size != second.size)
			throw new IllegalArgumentException("Sizes are not same!");

		Vector result = new Vector(this.size);
		for(int i=0; i < size; i++) {
			result.elements[i] = this.elements[i] + second.elements[i];
		}
		return result;
	}

	public double distance(Vector second) {
		if(this.size != second.size)
			throw new IllegalArgumentException("Sizes are not same!");

		return this.minus(second).length();

	}

	public double elementAt(int i) {
		return this.elements[i];
	}

	public Vector scale(double constant) {
		Vector v = new Vector(this.size);

		for(int i=0; i < size; i++) {
			v.elements[i] = this.elements[i] * constant;
		}
		return v;
	}

	public Vector unitVector() {
		if(this.length() == 0.0)
			throw new ArithmeticException("Zero vector cannot have unit vector");
		return this.scale(1.0 / this.length());
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append('(');
		for (int i = 0; i < size; i++) {
			s.append(elements[i]);
			if (i < size-1) s.append(", ");
		}
		s.append(')');
		return s.toString();
	}





}
