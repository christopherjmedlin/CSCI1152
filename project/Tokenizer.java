// Word and LinkedList imported from current directory

/**
 * Converts a file (in the form of an array of Strings) into its unique words.
 *
 * @author Christopher Medlin
 */
public class Tokenizer {
	
	private LinkedList<Word> words;
	private LinkedList<Word> uniqueWords;

	public Tokenizer(String[] file) {
		this.words = new LinkedList<Word>();

		// tokenize each line in the file
		for (int i = 0; i < file.length; i++) {
			this.tokenize(file[i], i);
		}
		this.findUniqueWords();
	}

	private void tokenize(String s, int lineNumber) {
		// add whitespace at end of line so the last word isn't ignored
		s += " ";

		int wordStart = 0;
		for (int i = 0; i < s.length(); i++) {
			// some words have dashes in the middle
			if (!Character.isAlphabetic(s.charAt(i))) {
				String substr;
				substr = s.substring(wordStart, i);
				// long sequences of non-alphabetic characters will result in
				// empty strings
				if (!substr.isEmpty()) {
					this.words.insert(new Word(substr, lineNumber, wordStart));
				}
				wordStart = i+1;
			}
		}
	}

	private void findUniqueWords() {
		Word[] arr = words.toArrayList().toArray(new Word[0]);
		Utilities.mergeSortWords(arr);
		this.uniqueWords = Utilities.withoutDuplicates(arr);
	}
	
	public int getWordCount() {
		return words.length();
	}

	public LinkedList<Word> getUniqueWords() {
		return this.uniqueWords;
	}

	/**
	 * Returns every word that matches the given string.
	 */
	public LinkedList<Word> search(String s) {
		return words.linearSearchAll(new Word(s, 0, 0));
	}
}
