package algonquin.cst8284.lab4;

public class SolidObject <T extends BasicShape> {
	
   // TODO: Use the UML diagram to create the members required
private double depth;
private  T shape;

protected SolidObject(T shape, double depth){
	setDepth(depth);
	setShape(shape);
	
}
protected SolidObject(T shape){
	this(shape,1);
}

public double getDepth(){
	return depth;
}
 
public void setDepth(double depth){
	this.depth=depth;
	
}
	

public   T getShape(){
return   shape;	
}
	
public void setShape(T shape){
	this.shape=shape;
}


public  String getName(){//rectangle must verify before square
   String str="";
	if (this.getShape() instanceof Rectangle){
	str="block";}
	else if( this.getShape() instanceof Square){
	   
	   str ="cube";}
	else if(this.getShape() instanceof Circle){
		str="cyld";
	}
	return str;
}
public double getVolume(){
	return getShape().getArea() *getDepth();
}
public double getSurfaceArea(){
	return (2*getShape().getArea()) +
			(getShape().getPerimeter()
					* getDepth());
}



}
