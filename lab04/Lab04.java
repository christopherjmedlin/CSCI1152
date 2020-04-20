/*
 * Author: Christopher Medlin
 * Email: cmedlin@cnm.edu
 * Date: 06 Feb 2020
 * Course: CSCI1152
 *
 * Converts the first argument from a decimal number to a binary number and the second
 * vise versa.
 */

public class Lab04 {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Lab04 <decimal> <binary>");
		} else {
			String bin = decimalToBinary(args[0]);
			String dec = binaryToDecimal(args[1]);
			System.out.printf("The conversion of positive decimal number %s to unsigned binary is %s%n",
					args[0], bin);
			System.out.printf("The conversion of unsigned binary number %s to positive decimal is %s",
					args[1], dec);
		}
	}
	
	private static String decimalToBinary(String decimal) {
		StringBuilder binary = new StringBuilder();
		int num = Integer.parseInt(decimal);
		while (num > 0) {
			binary.append((char)(num%2+'0'));
			num /= 2;
		}
		// because characters are being appended at end
		binary.reverse();
		return binary.toString();
	}

	private static String binaryToDecimal(String binary) {
		int decimal = 0;
		int power = 0;
		for (int i = binary.length()-1; i >= 0; i--, power++) {
			decimal += (int) (binary.charAt(i)-'0') * Math.pow(2, power);
		}
		return Integer.toString(decimal);
	}
}
