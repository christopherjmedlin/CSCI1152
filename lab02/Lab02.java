/*
 * Author: Christopher Medlin
 * Email: cmedlin@cnm.edu
 * Date: 06 Feb 2020
 * Course: CSCI1152
 *
 * Caclulates the total income, adjusted income, and total tax, given the inputted
 * number of exemptions, gross salary, interest income, capital gains, and charitable
 * contributions.
 */
import java.util.Scanner;

public class Lab02 {
	public static void main(String[] args) {
		// scanner for input
		Scanner input = new Scanner(System.in);
		
		int exemptions = 0;
		double salary = 0;
		double income = 0;
		double gains = 0;
		double contributions = 0;
		try {
			System.out.print("Number of Exemptions: ");
			exemptions = input.nextInt();
			System.out.print("Gross Salary: ");
			salary = input.nextDouble();
			System.out.print("Interest Income: ");
			income = input.nextDouble();
			System.out.print("Capital Gains: ");
			gains = input.nextDouble();
			System.out.print("Charitable Contributions: ");
			contributions = input.nextDouble();
		} catch (Exception e) {
			System.err.println("Improper number.");
			System.exit(1);
		}

		double totalIncome = salary+income+gains;
		double adjustedIncome = totalIncome-(exemptions*1500.00)-contributions;

		double totalTax = 0;
		if (adjustedIncome > 50000.00) {
			totalTax += (adjustedIncome - 50000.00) * 0.28;
			// 23% * (50000-32000) + 15% * (32000-10000) = 7440
			totalTax += 7440.00;
		}
		else if (adjustedIncome > 32000.00) {
			totalTax += (adjustedIncome - 32000.00) * 0.23;
			// 15% * (32000-10000) = 3300
			totalTax += 3300.00;
		}
		else if (adjustedIncome > 10000.00) {
			totalTax += (adjustedIncome - 10000.00) * 0.15;
		}
		// else it is 0

		System.out.printf("%-18s%10.2f%n", "Total Income:", totalIncome); 
		System.out.printf("%-18s%10.2f%n", "Adjusted Income:", adjustedIncome);
		System.out.printf("%-18s%10.2f", "Total Tax:", totalTax);
	}
}
