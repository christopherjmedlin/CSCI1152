/*
 * Author: Christopher Medlin
 * Email: cmedlin@cnm.edu
 * Date: 06 Feb 2020
 * Course: CSCI1152
 *
 * Finds the first and second fastest runner in a hardcoded array of times.
 */

class Lab03 {
	public static void main (String[] args) {
		String[] names = {
			"Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt", "Alex",
	 		"Emma", "John", "James", "Jane", "Emily", "Daniel", "Neda",
	 		"Aaron", "Kate" 
		};

		int[] times = {
	 		341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393, 299,
	 		343, 317, 265
   		};

		int i1 = fastestIndex(times);
		int i2 = secondFastestIndex(times, i1);

		System.out.printf("%-33s%-14sTotal time: %d minutes%n",
						  "The fastest runner is:", names[i1], times[i1]);
		System.out.printf("%-33s%-14sTotal time: %d minutes",
						  "The second fastest runner is:", names[i2], times[i2]);
	}

	public static int fastestIndex(int[] times) {
		int fastest = 0;
		
		for (int i = 0; i < times.length; i++) {
			if (times[i] <= times[fastest])
				fastest = i;
		}

		return fastest;
	}

	public static int secondFastestIndex(int[] times, int first) {
		int secondFastest = 0;
		
		for (int i = 0; i < times.length; i++) {
			if (times[i] <= times[secondFastest] && i != first)
				secondFastest = i;
		}

		return secondFastest;
	}
}
