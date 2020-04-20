import java.util.Scanner;

class JONGC_EX05 {
	public static void main(String[] args) {
		int[] nums = new int[5];
		
		System.out.println("Enter 5 numbers below:");
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < nums.length; i++) {
			nums[i] = input.nextInt();
		}
	
		int total = 0;
		for (int i = 0; i < nums.length; i++) {
			total += nums[i];
		}

		System.out.printf("Total: %d%n", total);
		System.out.printf("Average: %.3f%n", (float) total/nums.length);
	}
}
