/*
 * Author: Christopher Medlin
 * Email: cmedlin@cnm.edu
 * Date: 18 Apr 2020
 * Class: CSCI1152
 *
 * Prints all unique words in a file in order, and allows one to search for
 * words in that file.
 */

import java.io.IOException;
import java.util.Scanner;

public class Project {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java Project <file>");
			System.exit(1);
		}

		String[] file = null;
		try {
			file = Utilities.readFile(args[0]);
		} catch (IOException e) {
			// file not found
			System.err.printf("File not found: %s%n", args[0]);
			System.exit(1);
		} catch (IllegalStateException e) {
			// line length too long
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
		Tokenizer tokens = new Tokenizer(file);
		// print word count
		System.out.printf("The total number of words in the file is: %d%n", tokens.getWordCount());
		
		LinkedList<Word> uniqueWords = tokens.getUniqueWords();
		System.out.printf("The total number of different words in the file is: %d%n%n", uniqueWords.length());

		// print all unique words
		System.out.println(Utilities.formatWords(uniqueWords));

		Scanner scan = new Scanner(System.in);
		String input;
		System.out.print("Please enter a word to be searched: ");
	
		// while input is not EINPUT
		while ((input = scan.nextLine()).compareTo("EINPUT") != 0) {
			switch (validateInput(input)) {
				case 0: printSearchResult(input, tokens, file);
						break;
				case 1: System.out.println("Word can be no longer than 12 characters.");
						break;
				case 2: System.out.println("Invalid word.");
						break;
			}
			System.out.print("Please enter a word to be searched: ");
		}
		System.out.println("\nBye!");
	}

	// returns 0 if input is OK, 1 if it is too long, and 2 if it contains
	// invalid characters
	private static int validateInput(String input) {
		if (input.length() > 12) {
			return 1;
		}
		// non-alphabetic characters are not allowed
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isAlphabetic(input.charAt(i))) {
				return 2;
			}
		}
		return 0;
	}

	private static void printSearchResult(String input, Tokenizer tokens, String[] file) {
		// search for word
		LinkedList<Word> results = tokens.search(input);
		
		// iterate through results
		LinkedList.LinkedListIterator resultIter = results.iterator();
		while(resultIter.hasNext()) {
			Word w = (Word) resultIter.next();
			System.out.printf("\nLine number %d:%n", w.getLine()+1);
			System.out.println(file[w.getLine()]);

			// print at very start with no spaces if position is 0
			if (w.getPosition() == 0) {
				System.out.print("^\n\n");
			} else {
				// print a number of spaces equivalent to the word's
				// position
				System.out.printf("%" + w.getPosition() + "s", "");
				// print position indicator
				System.out.println("^\n");
			}
		}

		if (results.isEmpty()) {
			System.out.println("No such word.");
		}
	}
}
