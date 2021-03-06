package algonquin.cst8284.lab4;

public class Rectangle extends Square {

	private double height;

	public Rectangle() {

	}

	public Rectangle(double width, double height) {
		setWidth(width);
		setHeight(height);
	}

	public Rectangle(Rectangle rectangle) {

		this(rectangle.getWidth(), rectangle.getHeight());
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getHeight() {

		return height;

	}

	@Override
	public double getArea() {

		return getWidth() * height;
	}

	@Override
	public double getPerimeter() {

		return getWidth() * 2 + height * 2;
	}

	@Override
	public boolean equals(Object obj) {

		boolean isEqual = false;

		if (obj instanceof Rectangle 
			    && this.getHeight() == ((Rectangle) obj).getHeight() 
				&& super.equals(obj))
			isEqual = true;

		return isEqual;

	}

	@Override
	public String toString() {

		return "Rectangle Overrides " + super.toString();
	}
}
