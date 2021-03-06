package algonquin.cst8284.lab1;

public class ComplexCalculator {

	private java.util.Scanner op = new java.util.Scanner(System.in);
	private Complex c;

	public ComplexCalculator(Complex c1, Complex c2) {

		System.out.println("Which math operation do you wish to perform?  Enter +, -, *, /");
		char mathOp = op.nextLine().charAt(0);

		switch (mathOp) {
		case '+':
			c = plus(c1, c2);
			break;
		case '-':
			c = substract(c1, c2);
			break;
		case '*':
			c = multiply(c1, c2);
			break;
		case '/':
			c = divide(c1,c2);
			break; 
		default:
			System.out.println("Unknown operation requested");
		}

	}

	private Complex plus(Complex c1, Complex c2) {
		double real = c1.getReal() + c2.getReal();
		double imag = c1.getImag() + c2.getImag();
		return (new Complex(real, imag));
	}

	

	private Complex substract(Complex c1, Complex c2) {
		double real = c1.getReal() - c2.getReal();
		double imag = c1.getImag() - c2.getImag();
		return (new Complex(real, imag));
	}

	private Complex multiply(Complex c1, Complex c2) {
		double real = c1.getReal() * c2.getReal() - c1.getImag() * c2.getImag();
		double imag = c1.getReal() * c2.getImag() + c2.getReal() * c1.getImag();
		return (new Complex(real, imag));
	}
     
	private Complex divide(Complex c1, Complex c2) {
		 
		 double x=multiply(c2,conjugate(c2)).getReal();
		 Complex y=multiply(c1,conjugate(c2));
		if(x==0)
			 return new Complex(0,0);
		 else
			 return new Complex(y.getReal()/x, y.getImag()/x);
		 
		
		//return new Complex( multiply(c1,conjugate(c2)).getReal()/multiply(c2,conjugate(c2)).getReal(),
		//		multiply(c1,conjugate(c2)).getImag()/multiply(c2,conjugate(c2)).getReal() ) ;
		
		/*double real = (c1.getReal() * c2.getReal() + c1.getImag() * c2.getImag())
				/ (c2.getReal() * c2.getReal() + c2.getImag() * c2.getImag());
		double imag = (c2.getReal() * c1.getImag() - c1.getReal() * c2.getImag())
				/ (c2.getReal() * c2.getReal() + c2.getImag() * c2.getImag());
		return (new Complex(real, imag));*/
	}

	
	

	private  Complex conjugate(Complex c2) {
		return new Complex(c2.getReal(), -c2.getImag());
	}

	
	public Complex getComplexResult() {
		return c;
	}

	

	public String toString() {

		//return getComplexResult()+"";
		return c.toString();
	}

}
