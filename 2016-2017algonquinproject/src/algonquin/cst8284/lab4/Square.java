package algonquin.cst8284.lab4;

public class Square extends BasicShape {

	public Square() {

	}

	public Square(Square square) {
		this(square.getWidth());
	}

	public Square(double width) {
		setWidth(width);
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return getWidth() * getWidth();
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return this.getWidth() * 4;
	}

	@Override
	public String toString() {

		return "Square Overrides " + super.toString();
	}

	@Override
	public boolean equals(Object obj) {

		boolean isEqual = false;

		if (obj instanceof Square && this.getWidth() == ((Square) obj).getWidth())
			isEqual = true;
		
		return isEqual;

	}
}
