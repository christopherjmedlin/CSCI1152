public class MEDLINC_EX06 {
	public static void main (String[] args) {
		if (args.length != 3) {
			System.out.println("Please supply three integers as arguments.");
			System.exit(1);
		}
	
		int largest = Integer.parseInt(args[0]);
		for (int i = 1; i < args.length; i++) {
			int num = Integer.parseInt(args[i]);
			if (num > largest) {
				largest = num;
			};
		}

		System.out.printf("The largest number is %d.", largest);
	}
}
