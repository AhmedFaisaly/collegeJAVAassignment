package algonquin.cst8110.lab;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int	num; 
		int den;
		Scanner input=new Scanner(System.in);
		System.out.println("Enter numerator: ");
		num=input.nextInt();
		System.out.println("Enter denominator: ");
		den=input.nextInt();
		input.close();
		Fractional f=new Fractional();
		System.out.println(num+"/"+den+"="+f.fract(num, den));

	}

}
class Fractional{

	public Fractional() {
	}


	public  int gcd(int a,int b){
		int r;
		while(b!=0){
			r=a%b;
			a=b;
			b=r;

		}

		return a;
	}//end of gcd()

	public String fract(int c, int d){

		gcd(c,d);
		int m= c/gcd(c,d);
		int n= d/gcd(c,d);
		if(n<0){
			n=-n;
			return ("-"+m/n+" "+m%n+"/"+n);
		}
	
		
		else if(m!=0&&m<n){

			return (m+"/"+n);
		}
		else if(n!=0&&n<m&&m%n==0){
			return m/n+" ";
		}
		else if(m!=0&&n<m&&m%n!=0){

			return (m/n+" "+m%n+"/"+n);
		}
		else  if(m==0&&n!=0){
			return m+" ";
		}

		else if(m==n){
			return m/n+" ";
		}

		else {return "invalid";}


	}//end of fract()
}//end of class Fractional



