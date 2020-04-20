import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Contains simple utility functions.
 *
 * @author Christopher Medlin
 */
public class Utilities {

	/**
	 * Reads a file into an array of strings.
	 */
	public static String[] readFile(String filename) throws IOException {
		Scanner in = new Scanner(Paths.get(filename));
		ArrayList<String> lines = new ArrayList<String>();
		
		// while more lines to read
		while (in.hasNext()) {
			String line = in.nextLine();
			// enforce 80 char limit
			if (line.length() > 80) {
				throw new IllegalStateException("Line length must be less than or equal to 80.");
			}
			lines.add(line);
		}
		return lines.toArray(new String[0]);
	}

	/**
	 * Sorts the words lexicographically.
	 */
	public static void mergeSortWords(Word[] words) {
		mergeSortHelper(words, 0, words.length-1);
	}
	
	// recursion step for merge sort
	private static void mergeSortHelper(Word[] words, int left, int right) {
		if ((right-left)>=1) {
			int middle = (left + right)/2;
			
			// sort left half
			mergeSortHelper(words, left, middle);
			// sort right half
			mergeSortHelper(words, middle+1, right);
			// merge the sorted halves
			merge(words, left, middle, middle+1, right);
		}
	}

	private static void merge(Word[] words, int leftBegin, int leftEnd, int
			rightBegin, int rightEnd) {

		int l = leftBegin; // left index
		int r = rightBegin; // right index
		int i = leftBegin; // temp array index
		Word[] temp = new Word[words.length];

		while (l <= leftEnd && r <= rightEnd) {
			// if word in left arr lexicographically precedes word in right arr
			if (words[l].compareTo(words[r]) <= 0) {
				temp[i++] = words[l++];
			}
			// if it follows it
		   	else {
				temp[i++] = words[r++];
			}
		}

		// empty left arr
		if (l == rightBegin) {
			// copy right array
			while (r <= rightEnd) {
				temp[i++] = words[r++];
			}
		// empty right arr
		} else {
			// copy left arr
			while (l <= leftEnd) {
				temp[i++] = words[l++];
			}
		}

		for (int j = leftBegin; j <= rightEnd; j++) {
			words[j] = temp[j];
		}
	}
	
	/**
	 * Returns duplicate-less version of sorted array.
	 *
	 * @param words a pre-sorted array of words.
	 * @return unique words, still sorted
	 */
	public static LinkedList<Word> withoutDuplicates(Word[] words) {
		LinkedList<Word> newWords = new LinkedList<Word>();
		// since the array is sorted, duplicates will always be in repeating
		// sequences.
		Word repeating = new Word("", 0, 0);
		for (int i = 0; i < words.length; i++) {
			// if there is a new repeating sequence
			if (words[i].compareTo(repeating) != 0) {
				repeating = words[i];
				newWords.insert(repeating);
			}
		}
		return newWords;
	}
	
	/**
	 * Formats a list of words into a String in which they are each seperated
	 * by a space and seperated into appropriately sized lines.
	 */
	public static String formatWords(LinkedList<Word> words) {
		LinkedList.LinkedListIterator iter = words.iterator();
		StringBuilder formattedString = new StringBuilder();
		int currentLineLength = 0;
		while (iter.hasNext()) {
			String w = iter.next().toString();
			// the + 1 is for the spaces in between words
			currentLineLength += w.toString().length() + 1;
			// new line if the current line's length has exceeded 100
			if (currentLineLength > 80) {
				formattedString.append('\n');
				currentLineLength = 0;
			}
			formattedString.append(w.toString() + " ");
		}
		formattedString.append('\n');

		return formattedString.toString();
	}
}
