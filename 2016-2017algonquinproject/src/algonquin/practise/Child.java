package algonquin.practise;

public class Child extends Father{
	private String cloth;
	private int edu;
	public Child(String name, int age, String cloth, int edu) {
		super(name, age);
		// TODO Auto-generated constructor stub
		this.cloth=cloth;
		this.edu=edu;
	}
  
	  
	  public static void main(String [] args){
           Child c=new Child("wang",2,"yellow",3);
           
	  }
  }

