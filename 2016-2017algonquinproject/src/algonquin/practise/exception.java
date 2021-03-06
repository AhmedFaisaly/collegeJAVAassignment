package algonquin.practise;

public class exception {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     Teacher t= new Teacher("Tao");
     t.prelect();
	}

}
class Teacher{
	private String name;
	private Computer computer;
	Teacher(String name){
		this.name=name;
		computer=new Computer();
	}
	public void prelect(){
		try {
			computer.run();
			System.out.println("class");

		} catch (BlueScreenException | ShutException e) {
			// TODO Auto-generated catch block
			computer.reset();
		}finally{
			test();
		}
		
	}
	private void test() {
		// TODO Auto-generated method stub
		System.out.println("test");
	}
}
class Computer{
	private int status=2 ;
	public void run() throws BlueScreenException,ShutException{
		if (status ==1)
			throw new BlueScreenException("blue screen!!!!");
		if (status==2)
			throw new ShutException("computer shut down!!!");
		System.out.println("computer running");
	}
	public void reset(){
		System.out.println("reset");
	}
	
	
}
class BlueScreenException extends Exception{
	
	BlueScreenException(String str){
		super(str);
	}
} 
class ShutException extends Exception{
	ShutException(String str){
		super(str);
	}
}