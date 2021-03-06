package algonquin.cst8110.lab;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Wages {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);

		DecimalFormat df = new DecimalFormat("0.00");

		double numHours = 0;
		double hourRate = 0;
		double basePay = 0;
		double overTime = 0;
		double totalPay = 0;
		double taxDeduct = 0;
		double netPay = 0;
		System.out.println("Enther the number of hours worked:  ");
		numHours=input.nextDouble();
		System.out.println("Enter the rate of pay: ");

		hourRate=input.nextDouble();
		if (numHours <= 37.5 && numHours >= 0&&(numHours*hourRate < 1000)) {

			
				basePay = numHours * hourRate;
				totalPay = numHours * hourRate;
				taxDeduct = (0 * totalPay);
				overTime = 0.00 * (numHours-37.5)*hourRate;
				netPay = numHours * hourRate;
			} 
			else if ((numHours*hourRate>= 1000) && (numHours*hourRate <= 2000)&&numHours <= 37.5 && numHours >= 0) {
				basePay = numHours * hourRate;
				totalPay = numHours * hourRate;
				overTime = 0.00*(numHours-37.5)*hourRate;
				taxDeduct = 0.2 * totalPay;
				netPay = 0.8 * totalPay;

			}

			else {
				basePay = numHours * hourRate;
				totalPay = numHours * hourRate;
				overTime = 0*numHours*hourRate;
				taxDeduct = 0.3 * totalPay;
				netPay = 0.7 * totalPay;

			}

		if (numHours > 37.5) {
			if ( 37.5 * hourRate+(numHours - 37.5) * 1.5*hourRate< 1000) {
				basePay = 37.5 * hourRate;
				overTime = (numHours - 37.5) * 1.5*hourRate;
				totalPay = basePay + overTime;
				taxDeduct = 0 * totalPay;
				netPay = totalPay - taxDeduct;

			}

			else if ( (numHours - 37.5) * 1.5*hourRate+37.5 * hourRate>= 1000 &&  (numHours - 37.5) * 1.5*hourRate+37.5 * hourRate<= 2000) {
				basePay = 37.5 * hourRate;
				overTime = (numHours - 37.5) * 1.5*hourRate;
				totalPay = basePay + overTime;
				taxDeduct = 0.2 * totalPay;
				netPay = totalPay - taxDeduct;

			}

			else {

				basePay = 37.5 * hourRate;
				overTime = (numHours - 37.5) * 1.5*hourRate;
				totalPay = basePay + overTime;
				taxDeduct = 0.3 * totalPay;
				netPay = totalPay - taxDeduct;

			}

		}

		System.out.println("Base pay is " + df.format(basePay));
		System.out.println("Overtime pay is " + df.format(overTime));
		System.out.println("Total pay is " + df.format(totalPay));
		System.out.println("Tax deducted " + df.format(taxDeduct));
		System.out.println("Net pay is " + df.format(netPay));
	}
}