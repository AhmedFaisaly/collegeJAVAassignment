package algonquin.cst8110.lab;

import java.util.Scanner;

public class Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] count = new int[3];
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the phrase");
		String str = input.nextLine();

		int total = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 'a' && c <= 'z') 
				count[0]++;
			else if (c <= 'Z' && c >= 'A') 
				count[1]++;
			else 
				count[2]++;

		}
		for (int j= 0; j < count.length; j++) {
			total += count[j];
		}


		System.out.println("The total number of letters is " + total);
		System.out.println("The number of upper case letters is " + count[1]);
		System.out.println("The number of lower case letters is " + count[0]);
		System.out.println("The number of other letters is " + count[2]);
		


	}// end of main

}// end of class
