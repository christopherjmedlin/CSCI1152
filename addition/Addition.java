// Adds two numbers specified by user.
import java.util.Scanner;

public class Addition {
	public static void main(String[] args) {
		int num1 = 0;
		int num2 = 0;
		
		if (args.length == 2) {
			try {
				num1 = Integer.parseInt(args[0]);
				num2 = Integer.parseInt(args[1]);
			} catch (Exception e) {
				System.err.println("whaddup");
			}
		}
		else {
			// create Scanner to obtain integers from standard input.
			Scanner input = new Scanner(System.in);
			
			// prompt user
			System.out.print("Enter first integer: ");
			// obtain first integer
			num1 = input.nextInt();
			
			// prompt user again
			System.out.print("Enter second integer: ");
			// obtain second integer
			num2 = input.nextInt();
		}
		
		// output sum of integers
		System.out.printf("Sum is %d%n", num1+num2);
	}
}
