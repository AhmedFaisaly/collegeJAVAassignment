package algonquin.cst8284.lab1;

public class Complex {
	private double real = 0;
	private double imag = 0;

	// Complex constructor that takes in a single string, e.g. 2-4i
	public Complex(String cStr) {
		this(cStr.split("(?=\\+)|(?=\\-)")); // splits cStr at + or - into an
												// array of two strings
	}

	// Complex constructor that takes in an array of two strings, e.g.
	// cStr[0]="2", cStr[1]="-4i"
	private Complex(String[] cStr) {
		this(cStr[0], cStr[1]);
	}

	// Complex constructor that takes two separate strings as parameters, e.g.
	// "2" and "-4i"
	public Complex(String r, String i) {

		this(Integer.parseInt(r), Integer.parseInt(i.replace("i","")));

	}

	// Complex constructor that takes in two ints as parameters, e.g. 2 and -4
	public Complex(int r, int i) {
		this((double) r, (double) i);
	}

	// Complex constructor that takes in two ints and stores them as floats,
	// e.g. as 2.0 and -4.0
	public Complex(double r, double i) {
		this.setReal(r);
		this.setImag(i);
	}

	// default Complex constructor; required, but not used. DO NOT ALTER
	public Complex() {
	}
	public void setReal(double r) {
		this.real = r;

	}
	public void setImag(double i) {
		this.imag = i;
	}
	public double getReal() {

		return real;
	}

	public double getImag() {

		return imag;

	}

	public Complex getComplex() {
		return this;
	}       

	public String toString() {
		if (imag == 0)
			return real + "";
		if (real == 0)
			return imag + "i";
		if (imag < 0)
			return real + "-" + (-imag) + "i";
		return real + "+" + imag + "i";

	}

	
	

}
