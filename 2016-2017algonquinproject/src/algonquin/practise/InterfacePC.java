package algonquin.practise;

import java.lang.reflect.Array;
interface abc{}
interface USB{
	int NUM =3;
	 void open();
	public abstract void close();
}
public class InterfacePC {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
        useUSB(new Upan());
        
	}

   public static void useUSB(USB u){
	   u.open();
	   u.close();
   }
}

class Upan implements USB {
	public void open(){
		System.out.println("upan open");
	}
	public void close(){
		System.out.println("upan close");
	}
	
	interface a extends USB, abc{}
	
}