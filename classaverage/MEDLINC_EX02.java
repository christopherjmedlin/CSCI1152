// Fig 3.6 ClassAverage.java
// solving the class-average problem using counter-controlled iteration
import java.util.Scanner;

public class MEDLINC_EX02 {
	public static void main(String[] args) {
		// create Scanner for input
		Scanner input = new Scanner(System.in);

		// initialization phase
		int total = 0;
		int gradeCounter = 1;
		int numOfGrades = 5;
		boolean run = true; // loop does not run if run is false
		
		// check for number of grades argument
		if (args.length > 0) {
			if (args[0].equals("-h") || args[0].equals("--help")) {
				System.out.println("Calculate class average.");
				System.out.println("usage: java MEDLINC_EX02 <optional: number of students>");
				run = false;
			}
			else {
				try {
					numOfGrades = Integer.parseInt(args[0]);
				} catch (NumberFormatException e) {
					System.err.println("Invalid number of students.");
				}
				if (numOfGrades > 50) {
					System.err.println("Too many students.");
					run = false;
				}
			}
		}
		
		// processing phase uses counter iteration
		while (gradeCounter <= numOfGrades && run) {
			System.out.print("Enter grade: "); // prompt
			int grade = 0;
			try {
				grade = input.nextInt(); // input next grade
			} catch (Exception e) {
				run = false;
			}
			if (grade > 100 ) {
				run = false;
			}
			if (!run) {
				System.err.println("Invalid number.");
			}
			total = total + grade; // add grade to total
			gradeCounter = gradeCounter + 1; // increment counter
		}

		if (run) {
			// termination
			int average = total/numOfGrades;

			// display total and average of grades
			System.out.printf("%nTotal of all %d grades is %d%n", numOfGrades, total);
			System.out.printf("Class average is %d%n", average);
		}
	}
}
