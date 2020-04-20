/**
 * Represents a word found inside a file, along with the line and
 * position at which it occurs
 *
 * @author Christopher Medlin
 */
public class Word implements Comparable<Word> {
	private String word;
	private int line;
	private int position;
	
	public Word(String word, int line, int position) {
		this.word = word;
		this.line = line;
		this.position = position;
	}

	public String toString() {
		return this.word;
	}

	public int compareTo(Word w) {
		// compare the strings
		return this.word.compareTo(w.toString());
	}

	public int getLine() {
		return this.line;
	}
	
	public int getPosition() {
		return this.position;
	}
}
