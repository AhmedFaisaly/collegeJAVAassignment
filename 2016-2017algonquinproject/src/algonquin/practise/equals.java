package algonquin.practise;

public class equals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     String a="123456";
     String b="123456";
     String c = new String("123456");
     String d= new String (a);
     if (a==b)
    	 System.out.println("a=b");
     else
    	 System.out.println("a!=b");
     if (a.equals(b))
    	 System.out.println("a equals b");
     else
    	 System.out.println("a is not equal to b");
     if (a==c)
    	 System.out.println("a=c");
     else
    	 System.out.println("a!=c");
     if (a.equals(c))
    	 System.out.println("a equals c");
     else
    	 System.out.println("a is not equal to c");
     if (a==d)
    	 System.out.println("a=d");
     else
    	 System.out.println("a!=d");
     if (a.equals(d))
    	 System.out.println("a equals d");
     else
    	 System.out.println("a is not equal to d");
    
	}
 
}
